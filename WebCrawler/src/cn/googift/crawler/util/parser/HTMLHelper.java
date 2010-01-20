package cn.googift.crawler.util.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;


public class HTMLHelper {
    public static List<String> getTagContentWithAttribute(String html, String tagName, String attributeName, String attributeValue) {
        String tagStart = "<" + tagName;
        int index = html.indexOf(tagStart);
        List<String> contentList = new ArrayList<String>();
        while (index >= 0) {
            if (tagHasAttribute(html, index, attributeName, attributeValue)) {
                TagContentResult result = pickEnclosingTagContent(html, index, tagName);
                contentList.add(result.content);
                index = result.endIndex;
            }
            index = html.indexOf(tagStart, index + 1);
        }
        return contentList;
    }

    public static String htmlToString(String html) {
        String noHTMLString = html.replaceAll("\\<.*?\\>", "");
        noHTMLString = noHTMLString.replace("&lt;", "<");
        noHTMLString = noHTMLString.replace("&gt;", ">");
        noHTMLString = noHTMLString.replace("&amp;", "&");
        noHTMLString = noHTMLString.replace("&quot;", "\"");
//        noHTMLString = noHTMLString.replace("&reg;", "®");
//        noHTMLString = noHTMLString.replace("&trade;", "™");
//        noHTMLString = noHTMLString.replace("&ensp;", " "); //半个空白位
//        noHTMLString = noHTMLString.replace("&emsp;", " "); //一个空白位
        noHTMLString = noHTMLString.replace("&nbsp;", " ");
        return noHTMLString;
    }

    private static boolean tagHasAttribute(String html, final Integer index, String attributeName, String attributeValue) {
        int tagRight = html.indexOf(">", index + 1);
        if (tagRight < 0) return false;
        String tag = html.substring(index, tagRight);
        Pattern attributeP = Pattern.compile(attributeName + "[ ]*=[ ]*\"" + attributeValue + "\"");
        return attributeP.matcher(tag).find();
    }

    private static TagContentResult pickEnclosingTagContent(String html, int index, String tagName) {
        String content;
        String tagStart = "<" + tagName;
        String tagEnd = "</" + tagName;
        int depth = 1;
        int startIndex = index;
        while (depth > 0) {
            int is = html.indexOf(tagStart, index);
            int ie = html.indexOf(tagEnd, index);
            if (ie < 0) {
                return new TagContentResult(null, html.length()); // no end tag
            } else if (is < 0) {
                index = is + 1;
                depth--;
            } else if (is < ie) {
                index = is + 1;
                depth++;
            } else {
                index = ie + 1;
                depth--;
            }
        }
        content = html.substring(html.indexOf(">", startIndex) + 1, index);
        return new TagContentResult(content, index);
    }

    private static class TagContentResult {
        private String content;
        private int endIndex;

        private TagContentResult(String content, int endIndex) {
            this.content = content;
            this.endIndex = endIndex;
        }
    }
}
