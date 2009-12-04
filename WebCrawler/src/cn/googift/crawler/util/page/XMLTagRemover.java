package cn.googift.crawler.util.page;

public class XMLTagRemover {
    public static String removeTag(String xml) {
        if(null == xml) return null;
        String pattern = "<[/]?[a-zA-Z][a-zA-Z0-9]*[^>]*>";
        return xml.replaceAll(pattern, "");
    }
}
