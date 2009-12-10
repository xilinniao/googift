package cn.googift.crawler.util.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class RegexMatcher {
    private final Pattern pattern;

    public RegexMatcher(String pattern, boolean caseSensitive, boolean multipleLine) {
        if(caseSensitive) {
            if(multipleLine) this.pattern = Pattern.compile(pattern, Pattern.MULTILINE);
            else this.pattern = Pattern.compile(pattern);
        } else {
            if(multipleLine) this.pattern = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
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

    public List<String[]> matchAll(String content) {
        Matcher m = pattern.matcher(content);
        List<String[]> ms = new ArrayList<String[]>();
        while(m.find()) {
            String[] groups = new String[m.groupCount()];
            for(int i=0;i<groups.length;i++) {
                groups[i] = m.group(i + 1);
            }
            ms.add(groups);
        }
        return ms;
    }
}
