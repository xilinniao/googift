package cn.googift.recognition.image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.LookupOp;
import java.awt.image.ShortLookupTable;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

public class ImageHelper {
    public static void setBrightness(BufferedImage image, int x, int y, float value) {
        try {
            image.setRGB(x, y, new Color(value, value, value).getRGB());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static float getBrightness(BufferedImage image, int x, int y) {
        int r = image.getRaster().getSample(x, y, 0);
        int g = image.getRaster().getSample(x, y, 1);
        int b = image.getRaster().getSample(x, y, 2);
        if(r == 255 && g == 255 && b == 255) return 0;
        if(r > 200 && g < 100 && b < 100) return 1;
        return 0;
//        float[] hsb = Color.RGBtoHSB(r, g, b, null);
//        return hsb[2];
    }

    public static float getSaturation(BufferedImage image, int x, int y) {
        int r = image.getRaster().getSample(x, y, 0);
        int g = image.getRaster().getSample(x, y, 1);
        int b = image.getRaster().getSample(x, y, 2);

        float[] hsb = Color.RGBtoHSB(r, g, b, null);
        return hsb[1];
    }

    public static float getHue(BufferedImage image, int x, int y) {
        int r = image.getRaster().getSample(x, y, 0);
        int g = image.getRaster().getSample(x, y, 1);
        int b = image.getRaster().getSample(x, y, 2);

        float[] hsb = Color.RGBtoHSB(r, g, b, null);
        return hsb[0];
    }

    public static BufferedImage loadImage(String filepath) throws IOException {
        try {
            File source = new File(filepath);
            BufferedImage image = ImageIO.read(source);
            BufferedImage outimage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics2D g = outimage.createGraphics();
            g.drawImage(image, 0, 0, null);
            g.dispose();
            return outimage;
        } catch (IOException ex) {
            throw new IOException("{Error in image loader} Couldn't read input file " + filepath);
        }
    }

    public static void saveImage(BufferedImage image, String filepath) throws IOException {
        String type = filepath.substring(filepath.lastIndexOf('.') + 1, filepath.length()).toUpperCase();
        if (!type.equals("BMP") &&
                !type.equals("JPG") &&
                !type.equals("JPEG") &&
                !type.equals("PNG")
                ) throw new IOException("Unsupported file format");
        File destination = new File(filepath);
        ImageIO.write(image, type, destination);
    }

    public static BufferedImage linearResizeBi(BufferedImage origin, int width, int height) {
        BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = resizedImage.createGraphics();
        float xScale = (float) width / origin.getWidth();
        float yScale = (float) height / origin.getHeight();
        AffineTransform at = AffineTransform.getScaleInstance(xScale, yScale);
        g.drawRenderedImage(origin, at);
        g.dispose();
        return resizedImage;
    }

    public static BufferedImage averageResizeBi(BufferedImage origin, int width, int height) {
        if (origin.getWidth() < width || origin.getHeight() < height)
            return linearResizeBi(origin, width, height);

        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        float xScale = (float) origin.getWidth() / width;
        float yScale = (float) origin.getHeight() / height;

        for (int x = 0; x < width; x++) {
            int x0min = Math.round(x * xScale);
            int x0max = Math.round((x + 1) * xScale);

            for (int y = 0; y < height; y++) {
                int y0min = Math.round(y * yScale);
                int y0max = Math.round((y + 1) * yScale);

                float sum = 0;
                int sumCount = 0;

                for (int x0 = x0min; x0 < x0max; x0++) {
                    for (int y0 = y0min; y0 < y0max; y0++) {
                        sum += getBrightness(origin, x0, y0);
                        sumCount++;
                    }
                }
                sum /= sumCount;
                setBrightness(resized, x, y, sum);
            }
        }
        return resized;
    }

    public static BufferedImage duplicateBufferedImage(BufferedImage image) {
        BufferedImage imageCopy = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
        imageCopy.setData(image.getData());
        return imageCopy;
    }

    public static void thresholding(BufferedImage bi) {
        short[] threshold = new short[256];
        for (short i = 0; i < 36; i++) threshold[i] = 0;
        for (short i = 36; i < 256; i++) threshold[i] = i;
        BufferedImageOp thresholdOp = new LookupOp(new ShortLookupTable(0, threshold), null);
        thresholdOp.filter(bi, bi);
    }


    public static float[][] bufferedImageToArray(BufferedImage image, int w, int h) {
        float[][] array = new float[w][h];
        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                array[x][y] = getBrightness(image, x, y);
            }
        }
        return array;
    }

    public static float[][] bufferedImageToArrayWithBounds(BufferedImage image, int w, int h, int bound) {
        float[][] array = new float[w + 2 * bound][h + 2 * bound];

        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                array[x + bound][y + bound] = getBrightness(image, x, y);
            }
        }
        // set the horizontal bound
        for (int x = 0; x < w + 2 * bound; x++) {
            for (int i = 0; i < bound; i++) {
                array[x][i] = 1;
                array[x][h + i + 1] = 1;
            }
        }
        // set the vertical bound
        for (int y = 0; y < h + 2 * bound; y++) {
            for (int i = 0; i < bound; i++) {
                array[i][y] = 1;
                array[w + i + 1][y] = 1;
            }
        }
        return array;
    }

    public static BufferedImage arrayToBufferedImage(float[][] array, int w, int h) {
        BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                setBrightness(bi, x, y, array[x][y]);
            }
        }
        return bi;
    }

    public static BufferedImage createBlankBi(int width, int height) {
        return new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    }

    // sum the brightness
    public static BufferedImage sumBi(BufferedImage bi1, BufferedImage bi2) {
        BufferedImage out = new BufferedImage(Math.min(bi1.getWidth(), bi2.getWidth()),
                Math.min(bi1.getHeight(), bi2.getHeight()),
                BufferedImage.TYPE_INT_RGB);

        for (int x = 0; x < out.getWidth(); x++)
            for (int y = 0; y < out.getHeight(); y++) {
                setBrightness(out, x, y, (float) Math.min(1.0, getBrightness(bi1, x, y) + getBrightness(bi2, x, y)));
            }
        return out;
    }


//    public float getCharsWidthDispersion(Vector<Char> chars) {
//        float averageDispersion = 0;
//        float averageWidth = this.getAverageCharWidth(chars);
//
//        for (Char chr : chars)
//            averageDispersion += (Math.abs(averageWidth - chr.fullWidth));
//        averageDispersion /= chars.size();
//
//        return averageDispersion / averageWidth;
//    }
//    public float getPiecesWidthDispersion(Vector<Char> chars) {
//        float averageDispersion = 0;
//        float averageWidth = this.getAveragePieceWidth(chars);
//
//        for (Char chr : chars)
//            averageDispersion += (Math.abs(averageWidth - chr.pieceWidth));
//        averageDispersion /= chars.size();
//
//        return averageDispersion / averageWidth;
//    }
//
//    public float getAverageCharWidth(Vector<Char> chars) {
//        float averageWidth = 0;
//        for (Char chr : chars)
//            averageWidth += chr.fullWidth;
//        averageWidth /= chars.size();
//        return averageWidth;
//    }
//    public float getAveragePieceWidth(Vector<Char> chars) {
//        float averageWidth = 0;
//        for (Char chr : chars)
//            averageWidth += chr.pieceWidth;
//        averageWidth /= chars.size();
//        return averageWidth;
//    }
//
//    public float getAveragePieceHue(Vector<Char> chars) throws Exception {
//        float averageHue = 0;
//        for (Char chr : chars)
//            averageHue += chr.statisticAverageHue;
//        averageHue /= chars.size();
//        return averageHue;
//    }
//    public float getAveragePieceContrast(Vector<Char> chars) throws Exception {
//        float averageContrast = 0;
//        for (Char chr : chars)
//            averageContrast += chr.statisticContrast;
//        averageContrast /= chars.size();
//        return averageContrast;
//    }
//    public float getAveragePieceBrightness(Vector<Char> chars) throws Exception {
//        float averageBrightness = 0;
//        for (Char chr : chars)
//            averageBrightness += chr.statisticAverageBrightness;
//        averageBrightness /= chars.size();
//        return averageBrightness;
//    }
//    public float getAveragePieceMinBrightness(Vector<Char> chars) throws Exception {
//        float averageMinBrightness = 0;
//        for (Char chr : chars)
//            averageMinBrightness += chr.statisticMinimumBrightness;
//        averageMinBrightness /= chars.size();
//        return averageMinBrightness;
//    }
//    public float getAveragePieceMaxBrightness(Vector<Char> chars) throws Exception {
//        float averageMaxBrightness = 0;
//        for (Char chr : chars)
//            averageMaxBrightness += chr.statisticMaximumBrightness;
//        averageMaxBrightness /= chars.size();
//        return averageMaxBrightness;
//    }
//
//    public float getAveragePieceSaturation(Vector<Char> chars) throws Exception {
//        float averageSaturation = 0;
//        for (Char chr : chars)
//            averageSaturation += chr.statisticAverageSaturation;
//        averageSaturation /= chars.size();
//        return averageSaturation;
//    }
//
//    public float getAverageCharHeight(Vector<Char> chars) {
//        float averageHeight = 0;
//        for (Char chr : chars)
//            averageHeight += chr.fullHeight;
//        averageHeight /= chars.size();
//        return averageHeight;
//    }
//    public float getAveragePieceHeight(Vector<Char> chars) {
//        float averageHeight = 0;
//        for (Char chr : chars)
//            averageHeight += chr.pieceHeight;
//        averageHeight /= chars.size();
//        return averageHeight;
//    }

    public float getAverageCharSquare(Vector<Char> chars) {
        float average = 0;
        for (Char chr : chars)
            average += chr.getWidth() * chr.getHeight();
        average /= chars.size();
        return average;
    }

}
