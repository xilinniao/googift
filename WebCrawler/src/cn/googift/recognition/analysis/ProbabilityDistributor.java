package cn.googift.recognition.analysis;

import java.util.List;
import java.util.Vector;

public class ProbabilityDistributor {
    float center;
    float power;
    int leftMargin;
    int rightMargin;

    public ProbabilityDistributor(float center, float power, int leftMargin, int rightMargin) {
        this.center = center;
        this.power = power;
        this.leftMargin = Math.max(1, leftMargin);
        this.rightMargin = Math.max(1, rightMargin);
    }

    private float distributionFunction(float value, float positionPercentage) {
        return value * (1 - this.power * Math.abs(positionPercentage - this.center));
    }

    public List<Float> distribute(List<Float> peaks) {
        Vector<Float> distributedPeaks = new Vector<Float>();
        for (int i = 0; i < peaks.size(); i++) {
            if (i < leftMargin || i > peaks.size() - rightMargin) {
                distributedPeaks.add(0f);
            } else {
                distributedPeaks.add(distributionFunction(peaks.get(i),
                        ((float) i / peaks.size())
                )
                );
            }
        }

        return distributedPeaks;
    }
}
