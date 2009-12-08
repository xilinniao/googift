package cn.googift.crawler.sites.jingdong.recognize;

import cn.googift.crawler.util.page.PagePoller;
import cn.googift.recognition.Recognizer;
import cn.googift.recognition.image.implement.DefaultCharAnalysis;

import java.awt.image.BufferedImage;

public class JDPriceParser {
    private static final Recognizer recognizer = new Recognizer(PricePlateAnalysis.loadBaseChars(), PricePlateAnalysis.getBaseChars(), new PricePlateAnalysis(), new DefaultCharAnalysis());

    public static String parsePrice(String picLink) {
        if (null == picLink) return null;
        try {
            final BufferedImage image = PagePoller.pollImage(picLink);
            if (null == image) return null;
            return recognizer.recognize(image);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
