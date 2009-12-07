package cn.googift.crawler.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class FileUtil {
    public static List<File> loadFileList(String dirURL, Comparator<File> fileComparator) {
        File dir = new File(dirURL);
        if (dir.exists() && dir.isDirectory()) {
            final File[] files = dir.listFiles(new ImageFileFilter());
            final List<File> fileList = Arrays.asList(files);
            if(null != fileComparator) Collections.sort(fileList, fileComparator);
            return fileList;
        }
        throw new IllegalArgumentException("The designate url does not exist or is not a dir.");
    }

    public static List<BufferedImage> toImageList(List<File> fileList) {
        List<BufferedImage> images = new ArrayList<BufferedImage>(fileList.size());
            for (File file : fileList) {
                images.add(loadImage(file));
            }
        return images;
    }

    public static BufferedImage loadImage(File file) {
        try {
            BufferedImage image = ImageIO.read(file);
            BufferedImage outimage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics2D g = outimage.createGraphics();
            g.drawImage(image, 0, 0, null);
            g.dispose();
            return image;
        } catch (IOException ex) {
            throw new IllegalArgumentException("Can not load images!");
        }
    }
}
