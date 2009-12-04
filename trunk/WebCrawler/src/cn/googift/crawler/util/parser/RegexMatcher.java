package cn.googift.crawler.util.parser;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class RegexMatcher {
    private final Pattern pattern;

    public RegexMatcher(String pattern, boolean caseSensitive, boolean multiline) {
        if(caseSensitive) {
            if(multiline) this.pattern = Pattern.compile(pattern, Pattern.MULTILINE);
            else this.pattern = Pattern.compile(pattern);
        } else {
            if(multiline) this.pattern = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
            else this.pattern = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
        }
    }

    public String[] matchFirst(String content) {
        Matcher m = pattern.matcher(content);
        if(m.find()) {
            String[] groups = new String[m.groupCount()];
            for(int i=0;i<groups.length;i++) {
                groups[i] = m.group(i + 1);
            }
            return groups;
        }
        return null;
    }
}
