package cn.googift.crawler.util.parser;

import java.text.DecimalFormat;

public class PriceHandler {
    private static final DecimalFormat IndexStandardFormat = new DecimalFormat("0000000000.00");
    private static final DecimalFormat ShowingFormat = new DecimalFormat("0.00");

    public static Float parsePriceNumber(String content) {
        if (null == content) return null;
        content = removeNonNumber(content);
        return Float.parseFloat(content);
    }

    public static String toIndexStandardFormat(Float f) {
        if (null == f) return null;
        return IndexStandardFormat.format(f);
    }

    public static String toIndexStandardFormat(String s) {
        if (null == s) return null;
        try {
            double v = Double.parseDouble(s);
            return IndexStandardFormat.format(v);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static String toShowingFormat(String s) {
        if (null == s) return null;
        try {
            double v = Double.parseDouble(s);
            return ShowingFormat.format(v);
        } catch (NumberFormatException e) {
            return null;
        }
    }


    private static String removeNonNumber(String content) {
        return content.replaceAll("[^0-9\\.]+", "");
    }
}
