package cn.googift.recognition.recognizer;

public class RecognizedPattern {
    private char chr;
    private double cost;

    public RecognizedPattern(char chr, double value) {
        this.chr = chr;
        this.cost = value;
    }

    public char getChar() {
        return this.chr;
    }

    public double getCost() {
        return this.cost;
    }
}
