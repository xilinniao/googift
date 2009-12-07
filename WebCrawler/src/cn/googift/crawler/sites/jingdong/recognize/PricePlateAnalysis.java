package cn.googift.crawler.sites.jingdong.recognize;

import cn.googift.crawler.util.FileUtil;
import cn.googift.recognition.analysis.PlateAnalysis;
import cn.googift.recognition.analysis.graph.Graph;
import cn.googift.recognition.analysis.graph.Peak;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PricePlateAnalysis extends PlateAnalysis {
    public static char[] getBaseChars() {
        return new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '.', '￥'};
    }

    public static List<BufferedImage> loadBaseChars() {
        String imageDir = PricePlateAnalysis.class.getResource("/cn/googift/crawler/sites/jingdong/recognize/price_characters/").getPath();
        return FileUtil.toImageList(FileUtil.loadFileList(imageDir, new BaseImageFileComparator(getBaseChars())));
    }

    public List<Peak> findPeaks(Graph graph) {
        List<Peak> spaces = new ArrayList<Peak>();
        int last = 0;
        boolean zeroSeq = true;
        final List<Float> yValues = graph.getHistograms();
        for (int i = 0; i < yValues.size(); i++) {
            Float value = yValues.get(i);
            if (zeroSeq) {
                if (value != 0) {
                    last = i;
                    zeroSeq = false;
                }
            } else {
                if (value == 0) {
                    spaces.add(new Peak(last, i));
                    zeroSeq = true;
                }
            }
        }
        return spaces;
    }

    private static class BaseImageFileComparator implements Comparator<File> {
        private String chars;

        private BaseImageFileComparator(char[] chars) {
            this.chars = new String(chars);
        }

        public int compare(File o1, File o2) {
            final String name1 = o1.getName();
            final String name2 = o2.getName();
            final int i1 = chars.indexOf(name1.charAt(1));
            final int i2 = chars.indexOf(name2.charAt(1));
            if (i1 < 0 || i2 < 0) throw new IllegalStateException("File names and chars do not match!");
            return i1 - i2;
        }
    }
}
