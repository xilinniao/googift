package cn.googift.recognition.analysis;

import cn.googift.recognition.analysis.graph.Peak;

import java.util.Comparator;
import java.util.Vector;

public class PeakPositionComparer implements Comparator {
    Vector<Float> yValues = null;

    public PeakPositionComparer(Vector<Float> yValues) {
        this.yValues = yValues;
    }

    private float getPeakValue(Object peak) {
        return ((Peak) peak).getCenter();
    }

    public int compare(Object peak1, Object peak2) {
        double comparison = this.getPeakValue(peak2) - this.getPeakValue(peak1);
        if (comparison < 0) return 1;
        if (comparison > 0) return -1;
        return 0;
    }
}
