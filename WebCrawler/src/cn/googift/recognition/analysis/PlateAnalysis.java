package cn.googift.recognition.analysis;

import cn.googift.recognition.image.*;
import cn.googift.recognition.analysis.graph.Graph;
import cn.googift.recognition.analysis.graph.Peak;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Vector;

public abstract class PlateAnalysis {

    public List<Char> getChars(Plate plate) {
        Vector<Char> out = new Vector<Char>();

        List<Peak> peaks = findPeaks(histogram(plate.getBi()));

        for (Peak p : peaks) {
            if (p.getDiff() <= 0) continue;
            BufferedImage image = plate.getBi();
            final BufferedImage charImg = image.getSubimage(p.getLeft(), 0, p.getDiff(), image.getHeight());
            out.add(new Char(charImg, new PositionInPlate(p.getLeft(), p.getRight())));
        }
        return out;
    }

    protected Graph histogram(BufferedImage bi) {
        Graph graph = new Graph();
        for (int x = 0; x < bi.getWidth(); x++) {
            float counter = 0;
            for (int y = 0; y < bi.getHeight(); y++)
                counter += ImageHelper.getBrightness(bi, x, y);
            graph.addHistogram(counter);
        }
        return graph;
    }

    protected abstract List<Peak> findPeaks(Graph graph);

}
