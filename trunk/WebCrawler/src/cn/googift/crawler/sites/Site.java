package cn.googift.crawler.sites;

import cn.googift.crawler.page.PageLinkIterator;
import cn.googift.crawler.page.PageParser;

public abstract class Site {
    protected final String domain;

    protected Site(String domain) {
        this.domain = domain;
    }

    public String getDomain() {
        return domain;
    }

    public abstract PageLinkIterator getPageLinkIterator();

    public abstract PageParser getPageParser();
}
