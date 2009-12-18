package cn.googift.recognition.image.implement;

import cn.googift.recognition.analysis.CharAnalysis;
import cn.googift.recognition.image.BrightnessComputer;
import cn.googift.recognition.image.Char;
import cn.googift.recognition.image.ImageHelper;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Vector;

public class DefaultCharAnalysis extends CharAnalysis {
    private final BrightnessComputer brightnessComputer;

    public DefaultCharAnalysis(BrightnessComputer brightnessComputer) {
        this.brightnessComputer = brightnessComputer;
    }

    public List<Double> extractFeature(Char ch) {
        return extractEdgeFeatures(ch);
    }

    private static float[][] features = {
            {0, 1, 0, 1}, //0
            {1, 0, 1, 0}, //1
            {0, 0, 1, 1}, //2
            {1, 1, 0, 0}, //3
            {0, 0, 0, 1}, //4
            {1, 0, 0, 0}, //5
            {1, 1, 1, 0}, //6
            {0, 1, 1, 1}, //7
            {0, 0, 1, 0}, //8
            {0, 1, 0, 0}, //9
            {1, 0, 1, 1}, //10
            {1, 1, 0, 1}  //11
    };

    private List<Double> extractEdgeFeatures(Char ch) {
        final BufferedImage image = ch.getBi();
        int w = image.getWidth();
        int h = image.getHeight();
        double featureMatch;

        float[][] array = ImageHelper.bufferedImageToArray(image, w, h, brightnessComputer);

        double[] output = new double[features.length * 4];

        for (int f = 0; f < features.length; f++) {
            for (int my = 0; my < h - 1; my++) {
                for (int mx = 0; mx < w - 1; mx++) {
                    featureMatch = 0;
                    featureMatch += Math.abs(array[mx][my] - features[f][0]);
                    featureMatch += Math.abs(array[mx + 1][my] - features[f][1]);
                    featureMatch += Math.abs(array[mx][my + 1] - features[f][2]);
                    featureMatch += Math.abs(array[mx + 1][my + 1] - features[f][3]);

                    int bias = 0;
                    if (mx >= w / 2) bias += features.length;
                    if (my >= h / 2) bias += features.length * 2;
                    output[bias + f] += featureMatch < 0.05 ? 1 : 0;
                } // end my
            } // end mx
        } // end f
        Vector<Double> outputVector = new Vector<Double>();
        for (Double value : output) outputVector.add(value);
        return outputVector;
    }
}
