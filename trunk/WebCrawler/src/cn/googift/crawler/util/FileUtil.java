package cn.googift.crawler.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.List;

public class FileUtil {
    public static List<File> loadFileList(String dirURL, Comparator<File> fileComparator) {
        File dir = new File(dirURL);
        if (dir.exists() && dir.isDirectory()) {
            final File[] files = dir.listFiles(new ImageFileFilter());
            final List<File> fileList = Arrays.asList(files);
            if (null != fileComparator) Collections.sort(fileList, fileComparator);
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

    public static void writeImage(BufferedImage bi, String formatName, File file) {
        try {
            ImageIO.write(bi, formatName, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<URL> getURLList(File[] classesDirs, File[] libDirs) throws Exception {
        List<URL> urlList = new ArrayList<URL>();

        if (classesDirs != null) {
            for (int i = 0; i < classesDirs.length; i++) {
                File file = classesDirs[i];
                if (file == null) {
                    continue;
                }

                if (!file.exists() || !file.canRead()) {
                    continue;
                }

                URL url = null;
                if (file.isDirectory()) {
                    url = file.toURI().toURL();
                    urlList.add(url);
                } else {
                    if (file.getCanonicalPath().toLowerCase().endsWith(".jar")) {
                        url = file.toURI().toURL();
                        urlList.add(url);
                    }
                }
            }
        }

        if (libDirs != null) {
            for (int i = 0; i < libDirs.length; i++) {
                File directory = libDirs[i];
                if (directory == null) {
                    continue;
                }
                if (!directory.isDirectory() || !directory.exists()
                        || !directory.canRead()) {
                    continue;
                }
                String filenames[] = directory.list();
                for (int j = 0; j < filenames.length; j++) {
                    String filename = filenames[j].toLowerCase();
                    if (!filename.toLowerCase().endsWith(".jar")) {
                        continue;
                    }
                    File file = new File(directory, filenames[j]);
                    URL url = file.toURI().toURL();
                    urlList.add(url);
                }
            }
        }

        return urlList;
    }
}
