package cn.googift.recognition.algorithm;

import java.util.List;

public interface DistanceAlgorithm {
    double computeDistance(List<Double> vector1, List<Double> vector2);
}
