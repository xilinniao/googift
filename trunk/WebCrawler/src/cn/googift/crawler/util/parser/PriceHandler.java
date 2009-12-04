package cn.googift.crawler.util.parser;

public class PriceHandler {
    public static Float parsePriceNumber(String content) {
        content = removeNonNumber(content);
        return Float.parseFloat(content);
    }

    private static String removeNonNumber(String content) {
        return content.replaceAll("[^0-9\\.]+", "");
    }
}
