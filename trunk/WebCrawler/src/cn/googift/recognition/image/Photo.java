package cn.googift.recognition.image;

import cn.googift.recognition.analysis.HoughTransformation;
import cn.googift.recognition.analysis.Statistics;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.IOException;

public class Photo {
    protected BufferedImage image;

    public Photo(BufferedImage bi) {
        this.image = bi;
    }

    public Photo(String filepath) throws IOException {
        this.image = ImageHelper.loadImage(filepath);
    }

    public Photo clone() throws CloneNotSupportedException {
        super.clone();
        return new Photo(ImageHelper.duplicateBufferedImage(this.image));
    }

    public int getWidth() {
        return this.image.getWidth();
    }

    public int getHeight() {
        return this.image.getHeight();
    }

    public int getSquare() {
        return this.getWidth() * this.getHeight();
    }

    public BufferedImage getBi() {
        return this.image;
    }

    public BufferedImage getBiWithAxes() {
        BufferedImage axis = new BufferedImage(this.image.getWidth() + 40,
                this.image.getHeight() + 40, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphicAxis = axis.createGraphics();

        graphicAxis.setColor(Color.LIGHT_GRAY);
        Rectangle backRect = new Rectangle(0, 0, this.image.getWidth() + 40, this.image.getHeight() + 40);
        graphicAxis.fill(backRect);
        graphicAxis.draw(backRect);

        graphicAxis.drawImage(this.image, 35, 5, null);

        graphicAxis.setColor(Color.BLACK);
        graphicAxis.drawRect(35, 5, this.image.getWidth(), this.image.getHeight());

        for (int ax = 0; ax < this.image.getWidth(); ax += 50) {
            graphicAxis.drawString(Integer.toString(ax), ax + 35, axis.getHeight() - 10);
            graphicAxis.drawLine(ax + 35, this.image.getHeight() + 5, ax + 35, this.image.getHeight() + 15);
        }
        for (int ay = 0; ay < this.image.getHeight(); ay += 50) {
            graphicAxis.drawString(Integer.toString(ay), 3, ay + 15);
            graphicAxis.drawLine(25, ay + 5, 35, ay + 5);
        }
        graphicAxis.dispose();
        return axis;
    }

    public void setBrightness(int x, int y, float value) {
        image.setRGB(x, y, new Color(value, value, value).getRGB());
    }

    public float getBrightness(int x, int y) {
        return ImageHelper.getBrightness(image, x, y);
    }

    public float getSaturation(int x, int y) {
        return ImageHelper.getSaturation(image, x, y);
    }

    public float getHue(int x, int y) {
        return ImageHelper.getHue(image, x, y);
    }

    public void verticalEdgeDetector(BufferedImage source) {
        BufferedImage destination = ImageHelper.duplicateBufferedImage(source);

        float data1[] = {
                -1, 0, 1,
                -2, 0, 2,
                -1, 0, 1,
        };

//        float data2[] = {
//                1, 0, -1,
//                2, 0, -2,
//                1, 0, -1,
//        };

        new ConvolveOp(new Kernel(3, 3, data1), ConvolveOp.EDGE_NO_OP, null).filter(destination, source);
    }

    public void plainThresholding(Statistics stat) {
        int w = this.getWidth();
        int h = this.getHeight();
        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                this.setBrightness(x, y, stat.thresholdBrightness(this.getBrightness(x, y), 1.0f));
            }
        }
    }

//    public void adaptiveThresholding() {
////        Statistics stat = new Statistics(this);
//        int radius = 7;
////        if (radius == 0) {
////            plainThresholding(stat);
////            return;
////        }
//
//        int w = this.getWidth();
//        int h = this.getHeight();
//
//        float[][] sourceArray = ImageHelper.bufferedImageToArray(this.image, w, h);
//        float[][] destinationArray = ImageHelper.bufferedImageToArray(this.image, w, h);
//
//        int count;
//        float neighborhood;
//
//        for (int x = 0; x < w; x++) {
//            for (int y = 0; y < h; y++) {
//                // compute neighborhood
//                count = 0;
//                neighborhood = 0;
//                for (int ix = x - radius; ix <= x + radius; ix++) {
//                    for (int iy = y - radius; iy <= y + radius; iy++) {
//                        if (ix >= 0 && iy >= 0 && ix < w && iy < h) {
//                            neighborhood += sourceArray[ix][iy];
//                            count++;
//                        }
//                    }
//                }
//                neighborhood /= count;
//                if (destinationArray[x][y] < neighborhood) {
//                    destinationArray[x][y] = 0f;
//                } else {
//                    destinationArray[x][y] = 1f;
//                }
//            }
//        }
//        this.image = ImageHelper.arrayToBufferedImage(destinationArray, w, h);
//    }

    public HoughTransformation getHoughTransformation() {
        HoughTransformation hough = new HoughTransformation(this.getWidth(), this.getHeight());
        for (int x = 0; x < this.getWidth(); x++) {
            for (int y = 0; y < this.getHeight(); y++) {
                hough.addLine(x, y, this.getBrightness(x, y));
            }
        }
        return hough;
    }

}

