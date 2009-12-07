package cn.googift.recognition.analysis.graph;

public class Peak {
    public int left, center, right;
    public Peak(int left, int center, int right) {
        this.left = left;
        this.center = center;
        this.right = right;
    }
    public Peak(int left, int right) {
        this.left = left;
        this.center = (left+right)/2;
        this.right = right;
    }
    public int getLeft() {
        return this.left;
    }
    public int getRight() {
        return this.right;
    }
    public int getCenter() {
        return this.center;
    }
    public int getDiff() {
        return this.right - this.left;
    }
    public void setLeft(int left) {
        this.left = left;
    }
    public void setCenter(int center) {
        this.center = center;
    }
    public void setRight(int right) {
        this.right = right;
    }
}
