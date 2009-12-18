package cn.googift.crawler.sites.newegg.recognize;

import cn.googift.crawler.util.page.PagePoller;
import cn.googift.recognition.Recognizer;
import cn.googift.recognition.image.implement.DefaultCharAnalysis;

import java.awt.image.BufferedImage;

public class PriceParser {
    private static final PricePlateAnalysis pricePlateAnalysis = new PricePlateAnalysis();
    private static final Recognizer recognizer = new Recognizer(PricePlateAnalysis.loadBaseChars(), PricePlateAnalysis.getBaseChars(), pricePlateAnalysis, new DefaultCharAnalysis(pricePlateAnalysis));

    public static String parsePrice(String picLink) {
        if (null == picLink) return null;
        try {
            final BufferedImage image = PagePoller.pollImage(picLink);
            if (null == image) return null;
//            FileUtil.writeImage(image, "png", new File("c:\\" + image.hashCode() + ".png"));
            return recognizer.recognize(image);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}