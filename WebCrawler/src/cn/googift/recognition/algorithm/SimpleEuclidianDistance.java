package cn.googift.recognition.algorithm;

import java.util.List;

public class SimpleEuclidianDistance implements DistanceAlgorithm {
    public double computeDistance(List<Double> vector1, List<Double> vector2) {
        if(vector1.size() != vector2.size()) throw new IllegalArgumentException("Two vectors must be same long.");
        float diff = 0;
        float partialDiff;
        for (int x = 0; x < vector1.size(); x++) {
            partialDiff = (float) Math.abs(vector1.get(x) - vector2.get(x));
            diff += partialDiff * partialDiff;
        }
        return diff;
    }
}
