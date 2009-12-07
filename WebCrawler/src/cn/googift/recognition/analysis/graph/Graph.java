package cn.googift.recognition.analysis.graph;

import cn.googift.recognition.analysis.graph.Peak;
import cn.googift.recognition.analysis.ProbabilityDistributor;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Graph {
    protected List<Peak> peaks = null;
    protected List<Float> yValues = new ArrayList<Float>();

    private boolean actualAverageValue = false;
    private boolean actualMaximumValue = false;
    private boolean actualMinimumValue = false;
    private float averageValue;
    private float maximumValue;
    private float minimumValue;

    public void deActualizeFlags() {
        this.actualAverageValue = false;
        this.actualMaximumValue = false;
        this.actualMinimumValue = false;
    }

    // Check whether the given xPosition is not in any of the peaks.
    public static boolean allowedInterval(List<Peak> peaks, int xPosition) {
        for (Peak peak : peaks)
            if (peak.getLeft() <= xPosition && xPosition <= peak.getRight()) return false;
        return true;
    }

    public void addHistogram(float value) {
        yValues.add(value);
        this.deActualizeFlags();
    }

    public void applyProbabilityDistributor(ProbabilityDistributor probability) {
        this.yValues = probability.distribute(this.yValues);
        this.deActualizeFlags();
    }

    public void negate() {
        float max = this.getMaxValue();
        for (int i = 0; i < this.yValues.size(); i++)
            this.yValues.set(i, max - this.yValues.get(i));

        this.deActualizeFlags();
    }

    float getAverageValue() {
        if (!this.actualAverageValue) {
            float sum = 0.0f;
            for (Float yValue : this.yValues) sum += yValue.doubleValue();
            this.averageValue = sum / this.yValues.size();
            this.actualAverageValue = true;
        }
        return this.averageValue;
    }

    float getMaxValue() {
        if (!this.actualMaximumValue) {
            float maxValue = 0.0f;
            for (Float yValue : this.yValues) maxValue = Math.max(maxValue, yValue);
            this.maximumValue = maxValue;
            this.actualMaximumValue = true;
        }
        return this.maximumValue;
    }

    float getMinValue() {
        if (!this.actualMinimumValue) {
            float minValue = Float.POSITIVE_INFINITY;
            for (Float yValue : this.yValues) minValue = Math.min(minValue, yValue);
            this.minimumValue = minValue;
            this.actualMinimumValue = true;
        }
        return this.minimumValue;
    }

    int getMinValueIndex(int a, int b) {
        float minValue = Float.POSITIVE_INFINITY;
        int minIndex = b;
        for (int i = a; i < b; i++) {
            if (yValues.get(i) <= minValue) {
                minValue = yValues.get(i);
                minIndex = i;
            }
        }
        return minIndex;
    }

    public BufferedImage renderHorizontally(int width, int height) {
        BufferedImage content = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        BufferedImage axis = new BufferedImage(width + 40, height + 40, BufferedImage.TYPE_INT_RGB);

        Graphics2D graphicContent = content.createGraphics();
        Graphics2D graphicAxis = axis.createGraphics();

        Rectangle backRect = new Rectangle(0, 0, width + 40, height + 40);
        graphicAxis.setColor(Color.LIGHT_GRAY);
        graphicAxis.fill(backRect);
        graphicAxis.draw(backRect);
        backRect = new Rectangle(0, 0, width, height);
        graphicContent.setColor(Color.WHITE);
        graphicContent.fill(backRect);
        graphicContent.draw(backRect);


        int x, y, x0, y0;
        x = 0;
        y = 0;

        graphicContent.setColor(Color.GREEN);

        for (int i = 0; i < this.yValues.size(); i++) {
            x0 = x;
            y0 = y;
            x = (int) (((float) i / this.yValues.size()) * width);
            y = (int) (((float) 1 - (this.yValues.get(i) / this.getMaxValue())) * height);
            graphicContent.drawLine(x0, y0, x, y);
        }

        if (this.peaks != null) { // uz boli vyhladane aj peaky, renderujeme aj tie
            graphicContent.setColor(Color.RED);
            int i = 0;
            double multConst = (double) width / this.yValues.size();
            for (Peak p : this.peaks) {
                graphicContent.drawLine((int) (p.left * multConst), 0, (int) (p.center * multConst), 30);
                graphicContent.drawLine((int) (p.center * multConst), 30, (int) (p.right * multConst), 0);
                graphicContent.drawString((i++) + ".", (int) (p.center * multConst) - 5, 42);
            }
        }

        graphicAxis.drawImage(content, 35, 5, null);

        graphicAxis.setColor(Color.BLACK);
        graphicAxis.drawRect(35, 5, content.getWidth(), content.getHeight());

        for (int ax = 0; ax < content.getWidth(); ax += 50) {
            graphicAxis.drawString(Integer.toString(ax), ax + 35, axis.getHeight() - 10);
            graphicAxis.drawLine(ax + 35, content.getHeight() + 5, ax + 35, content.getHeight() + 15);
        }

        for (int ay = 0; ay < content.getHeight(); ay += 20) {
            graphicAxis.drawString(
                    Integer.toString(new Float((1 - (float) ay / content.getHeight()) * 100).intValue()) + "%"
                    , 1, ay + 15);
            graphicAxis.drawLine(25, ay + 5, 35, ay + 5);
        }
        graphicContent.dispose();
        graphicAxis.dispose();
        return axis;
    }

    public BufferedImage renderVertically(int width, int height) {
        BufferedImage content = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        BufferedImage axis = new BufferedImage(width + 10, height + 40, BufferedImage.TYPE_INT_RGB);

        Graphics2D graphicContent = content.createGraphics();
        Graphics2D graphicAxis = axis.createGraphics();

        Rectangle backRect = new Rectangle(0, 0, width + 40, height + 40);
        graphicAxis.setColor(Color.LIGHT_GRAY);
        graphicAxis.fill(backRect);
        graphicAxis.draw(backRect);
        backRect = new Rectangle(0, 0, width, height);
        graphicContent.setColor(Color.WHITE);
        graphicContent.fill(backRect);
        graphicContent.draw(backRect);


        int x, y, x0, y0;
        x = width;
        y = 0;

        graphicContent.setColor(Color.GREEN);

        for (int i = 0; i < this.yValues.size(); i++) {
            x0 = x;
            y0 = y;
            y = (int) (((float) i / this.yValues.size()) * height);
            x = (int) (((float) (this.yValues.get(i) / this.getMaxValue())) * width);
            graphicContent.drawLine(x0, y0, x, y);
        }

        if (this.peaks != null) { // uz boli vyhladane aj peaky, renderujeme aj tie
            graphicContent.setColor(Color.RED);
            int i = 0;
            double multConst = (double) height / this.yValues.size();
            for (Peak p : this.peaks) {
                graphicContent.drawLine(width, (int) (p.left * multConst), width - 30, (int) (p.center * multConst));
                graphicContent.drawLine(width - 30, (int) (p.center * multConst), width, (int) (p.right * multConst));
                graphicContent.drawString((i++) + ".", width - 38, (int) (p.center * multConst) + 5);
            }
        }

        graphicAxis.drawImage(content, 5, 5, null);

        graphicAxis.setColor(Color.BLACK);
        graphicAxis.drawRect(5, 5, content.getWidth(), content.getHeight());

        graphicContent.dispose();
        graphicAxis.dispose();
        return axis;
    }


    public void rankFilter(int size) {
        int halfSize = size / 2;
        Vector<Float> clone = new Vector<Float>(this.yValues);

        for (int i = halfSize; i < this.yValues.size() - halfSize; i++) {
            float sum = 0;
            for (int ii = i - halfSize; ii < i + halfSize; ii++) {
                sum += clone.elementAt(ii);
            }
            this.yValues.set(i, sum / size);
        }

    }

    public int indexOfLeftPeakRel(int peak, double peakFootConstantRel) {
        int index = peak;
        for (int i = peak; i >= 0; i--) {
            index = i;
            if (yValues.get(index) < peakFootConstantRel * yValues.get(peak)) break;
        }
        return Math.max(0, index);
    }

    public int indexOfRightPeakRel(int peak, double peakFootConstantRel) {
        int index = peak;
        for (int i = peak; i < yValues.size(); i++) {
            index = i;
            if (yValues.get(index) < peakFootConstantRel * yValues.get(peak)) break;
        }
        return Math.min(yValues.size(), index);
    }

    public float averagePeakDiff(Vector<Peak> peaks) { // not used
        float sum = 0;
        for (Peak p : peaks)
            sum += p.getDiff();
        return sum / peaks.size();
    }

    public float maximumPeakDiff(Vector<Peak> peaks, int from, int to) {
        float max = 0;
        for (int i = from; i <= to; i++)
            max = Math.max(max, peaks.elementAt(i).getDiff());
        return max;
    }

    public List<Float> getHistograms() {
        return yValues;
    }

}


