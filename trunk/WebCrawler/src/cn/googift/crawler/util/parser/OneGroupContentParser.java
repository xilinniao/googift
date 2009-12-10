package cn.googift.crawler.util.parser;

import java.util.ArrayList;
import java.util.List;

public class OneGroupContentParser {
    public static String pickContent(String pattern, String content) {
        RegexMatcher rm = new RegexMatcher(pattern, false, true);
        final String[] strings = rm.matchFirst(content);
        if (null != strings) return strings[0];
        return null;
    }

    public static List<String> pickMultipleOccurs(String pattern, String content) {
        RegexMatcher rm = new RegexMatcher(pattern, false, true);
        List<String[]> strings = rm.matchAll(content);
        if(null != strings) {
            List<String> occurs = new ArrayList<String>(strings.size());
            for(String[] s : strings) {
                if(null != s && s.length > 0) occurs.add(s[0]);
            }
            return occurs;
        }
        return null;
    }
}
