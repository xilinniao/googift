package cn.googift.recognition.image;

import java.awt.image.BufferedImage;

public class Char extends Photo {
    private PositionInPlate positionInPlate = null;

    public Char(BufferedImage bi) {
        this(bi, null);
    }

    public Char(BufferedImage bi, PositionInPlate positionInPlate) {
        super(bi);
        this.positionInPlate = positionInPlate;
    }

    public void setPositionInPlate(PositionInPlate positionInPlate) {
        this.positionInPlate = positionInPlate;
    }

    public PositionInPlate getPositionInPlate() {
        return positionInPlate;
    }

}



