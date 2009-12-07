package cn.googift.recognition;

import cn.googift.recognition.image.Char;
import cn.googift.recognition.image.Plate;
import cn.googift.recognition.recognizer.RecognizedChar;
import cn.googift.recognition.recognizer.RecognizedPlate;
import cn.googift.recognition.recognizer.RecognizedPattern;
import cn.googift.recognition.analysis.PlateAnalysis;
import cn.googift.recognition.analysis.CharAnalysis;
import cn.googift.recognition.algorithm.DistanceAlgorithm;
import cn.googift.recognition.algorithm.SimpleEuclidianDistance;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.ArrayList;

public class Recognizer {
    private final List<List<Double>> baseCharsFeature;
    private final char[] chars;
    private final PlateAnalysis plateAnalysis;
    private final CharAnalysis charAnalysis;

    private DistanceAlgorithm disAlg = new SimpleEuclidianDistance();

    public Recognizer(List<BufferedImage> baseCharImage, char[] baseChars, PlateAnalysis plateAnalysis, CharAnalysis charAnalysis) {
        this.baseCharsFeature = new ArrayList<List<Double>>(baseCharImage.size());
        this.chars = baseChars;
        this.plateAnalysis = plateAnalysis;
        this.charAnalysis = charAnalysis;
        if (baseCharImage.size() != baseChars.length) {
            throw new IllegalArgumentException("The numbers of images and alpha string are not matched!");
        }

        for (BufferedImage bi : baseCharImage) {
            Char imgChar = new Char(bi);
            this.baseCharsFeature.add(charAnalysis.extractFeature(imgChar));
        }
    }

    public void setDisAlg(DistanceAlgorithm disAlg) {
        this.disAlg = disAlg;
    }

    public String recognize(BufferedImage image) throws Exception {
        Plate plate = new Plate(image);
        List<Char> chars = plateAnalysis.getChars(plate);
        RecognizedPlate recognizedPlate = new RecognizedPlate();

        for (Char chr : chars) {
            RecognizedChar rc;
            rc = recognizeChar(chr);
            recognizedPlate.addChar(rc);
        }
        return recognizedPlate.getString();
    }

    private RecognizedChar recognizeChar(Char chr) throws Exception {
        List<Double> tested = charAnalysis.extractFeature(chr);
        RecognizedChar recognized = new RecognizedChar();
        for (int x = 0; x < baseCharsFeature.size(); x++) {
            double fx = disAlg.computeDistance(tested, baseCharsFeature.get(x));
            recognized.addPattern(new RecognizedPattern(chars[x], fx));
        }
        recognized.sort();
        return recognized;
    }

}
