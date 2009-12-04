package cn.googift.recognition;

import cn.googift.recognition.imageanalysis.Char;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;


public class KnnPatternClassificator extends CharacterRecognizer {
    List<Vector<Double>> learnVectors;

    public KnnPatternClassificator(List<BufferedImage> standChars, String alphaString) {
        if (standChars.size() != alphaString.length())
            throw new IllegalArgumentException("The numbers of images and alpha string are not matched!");
        this.learnVectors = new ArrayList<Vector<Double>>();
        for (BufferedImage bi : standChars) {
            Char imgChar = new Char(bi);
            imgChar.normalize();
            this.learnVectors.add(imgChar.extractFeatures());
        }
    }

    public RecognizedChar recognize(Char chr) throws Exception {
        Vector<Double> tested = chr.extractFeatures();
        int minx = 0;
        float minfx = Float.POSITIVE_INFINITY;

        RecognizedChar recognized = new RecognizedChar();

        for (int x = 0; x < this.learnVectors.size(); x++) {
            // pre lepsie fungovanie bol pouhy rozdiel vektorov nahradeny euklidovskou vzdialenostou
            float fx = this.simplifiedEuclideanDistance(tested, this.learnVectors.get(x));

            recognized.addPattern(recognized.new RecognizedPattern(this.alphabet[x], fx));

            //if (fx < minfx) {
            //    minfx = fx;
            //    minx = x;
            //}
        }
//        return new RecognizedChar(this.alphabet[minx], minfx);
        recognized.sort(0);
        return recognized;
    }

    public float difference(Vector<Double> vectorA, Vector<Double> vectorB) {
        float diff = 0;
        for (int x = 0; x < vectorA.size(); x++) {
            diff += Math.abs(vectorA.elementAt(x) - vectorB.elementAt(x));
        }
        return diff;
    }

    public float simplifiedEuclideanDistance(Vector<Double> vectorA, Vector<Double> vectorB) {
        float diff = 0;
        float partialDiff;
        for (int x = 0; x < vectorA.size(); x++) {
            partialDiff = (float) Math.abs(vectorA.elementAt(x) - vectorB.elementAt(x));
            diff += partialDiff * partialDiff;
        }
        return diff;
    }

}
