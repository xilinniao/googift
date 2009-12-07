package cn.googift.recognition.analysis;

import cn.googift.recognition.image.Char;

import java.util.List;

public abstract class CharAnalysis {
    public abstract List<Double> extractFeature(Char ch);
}
