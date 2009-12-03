package cn.googift.crawler.page;

public class Page {
    private final String URL;
    private final String content;

    public Page(String URL, String content) {
        this.URL = URL;
        this.content = content;
    }

    public String getURL() {
        return URL;
    }

    public String getContent() {
        return content;
    }
}
