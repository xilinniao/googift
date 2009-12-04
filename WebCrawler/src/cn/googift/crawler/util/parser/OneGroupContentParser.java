package cn.googift.crawler.util.parser;

public class OneGroupContentParser {
    public static String pickContent(String pattern, String content) {
        RegexMatcher rm = new RegexMatcher(pattern, false, true);
        final String[] strings = rm.matchFirst(content);
        if (null != strings) return strings[0];
        return null;
    }
}
