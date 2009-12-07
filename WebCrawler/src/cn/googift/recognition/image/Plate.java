package cn.googift.recognition.image;

import cn.googift.recognition.analysis.graph.PlateHorizontalGraph;
import cn.googift.recognition.analysis.graph.PlateVerticalGraph;
import cn.googift.recognition.analysis.ProbabilityDistributor;

import java.awt.image.BufferedImage;


public class Plate extends Photo {
    static public ProbabilityDistributor distributor = new ProbabilityDistributor(0, 0, 0, 0);

    protected BufferedImage copyImage;

    public Plate(BufferedImage bi) {
        super(bi);
        this.copyImage = this.image;
    }



    protected PlateVerticalGraph histogramYaxis(BufferedImage bi) {
        PlateVerticalGraph graph = new PlateVerticalGraph(this);
        int w = bi.getWidth();
        int h = bi.getHeight();
        for (int y = 0; y < h; y++) {
            float counter = 0;
            for (int x = 0; x < w; x++)
                counter += ImageHelper.getBrightness(bi, x, y);
            graph.addHistogram(counter);
        }
        return graph;
    }

    protected PlateHorizontalGraph histogramXaxis(BufferedImage bi) {
        PlateHorizontalGraph graph = new PlateHorizontalGraph(this);
        int w = bi.getWidth();
        int h = bi.getHeight();
        for (int x = 0; x < w; x++) {
            float counter = 0;
            for (int y = 0; y < h; y++)
                counter += ImageHelper.getBrightness(bi, x, y);
            graph.addHistogram(counter);
        }
        return graph;
    }

}
