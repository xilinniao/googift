package cn.googift.recognition.image;

import java.awt.image.BufferedImage;

public interface BrightnessComputer {
     float getBrightness(BufferedImage bi, int x, int y);
}
