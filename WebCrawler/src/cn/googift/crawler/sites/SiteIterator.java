package cn.googift.crawler.sites;

import java.util.Iterator;
import java.util.List;

public class SiteIterator implements Iterator<Site> {
    private final List<Site> sites;
    private int index;

    public SiteIterator(List<Site> sites) {
        this.sites = sites;
        this.index = 0;
    }

    public boolean hasNext() {
        return index >= sites.size();
    }

    public Site next() {
        return sites.get(index);
    }

    public void remove() {
        sites.remove(index);
    }
}
