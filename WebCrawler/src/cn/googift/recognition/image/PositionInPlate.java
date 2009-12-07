package cn.googift.recognition.image;

public class PositionInPlate {
    private final int x1;
    private final int x2;
    public PositionInPlate(int x1,int x2) {
        this.x1 = x1;
        this.x2 = x2;
    }

    public int getX1() {
        return x1;
    }

    public int getX2() {
        return x2;
    }

}