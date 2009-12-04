package cn.googift.crawler.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {
    public static List<BufferedImage> loadImageList(String dirURL) {
        File dir = new File(dirURL);
        List<BufferedImage> images = new ArrayList<BufferedImage>();
        if (dir.exists() && dir.isDirectory()) {
            for (File file : dir.listFiles()) {
                if (!ImageFileFilter.accept(file.getName())) continue; // not a image
                images.add(loadImage(file));
            }
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
