package cn.googift.crawler.util;

import java.awt.image.BufferedImage;
import java.awt.*;

public class ImageHelper {
    public static Image scaleToFitHeight(BufferedImage image, int height) {
        int w = image.getWidth();
        int h = image.getHeight();
        double scale = height / (double)h;
        int width = (int) (w * scale);
        return image.getScaledInstance(width, height, BufferedImage.SCALE_SMOOTH);
    }
}
