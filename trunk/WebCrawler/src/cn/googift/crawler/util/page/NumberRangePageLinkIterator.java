package cn.googift.crawler.util.page;

import cn.googift.crawler.page.PageLinkIterator;

public class NumberRangePageLinkIterator implements PageLinkIterator {
    private final int endId;
    private final String linkPrefix;
    private final String linkSufffix;

    private int currentId;

    public NumberRangePageLinkIterator(int startId, int endId, String linkPrefix, String linkSufffix) {
        this.currentId = startId;
        this.endId = endId;
        this.linkPrefix = linkPrefix;
        this.linkSufffix = linkSufffix;
    }

    public boolean hasNext() {
        return currentId < endId;
    }

    public String next() {
        currentId++;
        return linkPrefix + currentId + linkSufffix;
    }

    public void remove() {

    }
}
