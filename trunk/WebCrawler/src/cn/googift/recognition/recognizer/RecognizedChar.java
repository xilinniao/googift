package cn.googift.recognition.recognizer;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class RecognizedChar {
    private List<RecognizedPattern> patterns;
    private boolean isSorted;

    public RecognizedChar() {
        this.patterns = new ArrayList<RecognizedPattern>();
        this.isSorted = false;
    }

    public void addPattern(RecognizedPattern pattern) {
        this.patterns.add(pattern);
    }

    public boolean isSorted() {
        return this.isSorted;
    }

    public void sort() {
        if (this.isSorted) return;
        this.isSorted = true;
        Collections.sort(patterns, new PatternComparer(0));
    }

    public List<RecognizedPattern> getPatterns() {
        if (this.isSorted) return this.patterns;
        return null;
    }

    public RecognizedPattern getPattern(int i) {
        if (this.isSorted) return this.patterns.get(i);
        return null;
    }

    public BufferedImage render() {
        int width = 500;
        int height = 200;
        BufferedImage histogram = new BufferedImage(width + 20, height + 20, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphic = histogram.createGraphics();

        graphic.setColor(Color.LIGHT_GRAY);
        Rectangle backRect = new Rectangle(0, 0, width + 20, height + 20);
        graphic.fill(backRect);
        graphic.draw(backRect);

        graphic.setColor(Color.BLACK);

        int colWidth = width / this.patterns.size();
        int left, top;


        for (int ay = 0; ay <= 100; ay += 10) {
            int y = 15 + (int) ((100 - ay) / 100.0f * (height - 20));
            graphic.drawString(Integer.toString(ay), 3, y + 11);
            graphic.drawLine(25, y + 5, 35, y + 5);
        }
        graphic.drawLine(35, 19, 35, height);

        graphic.setColor(Color.BLUE);

        for (int i = 0; i < patterns.size(); i++) {
            left = i * colWidth + 42;
            top = height - (int) (patterns.get(i).getCost() * (height - 20));

            graphic.drawRect(left, top, colWidth - 2, height - top);
            graphic.drawString(patterns.get(i).getChar() + " ", left + 2, top - 8);
        }
        return histogram;
    }

    private class PatternComparer implements Comparator<RecognizedPattern> {
        int direction;

        public PatternComparer(int direction) {
            this.direction = direction;
        }

        public int compare(RecognizedPattern o1, RecognizedPattern o2) {
            double cost1 = o1.getCost();
            double cost2 = o2.getCost();

            int ret = 0;

            if (cost1 < cost2) ret = -1;
            if (cost1 > cost2) ret = 1;
            if (direction == 1) ret *= -1;
            return ret;
        }
    }
}
