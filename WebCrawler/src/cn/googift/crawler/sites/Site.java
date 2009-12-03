package cn.googift.crawler.sites;

import cn.googift.crawler.page.PageController;

public abstract class Site {
    protected final String siteId;

    protected Site(String siteId) {
        this.siteId = siteId;
    }

    public String getSiteId() {
        return siteId;
    }

    public abstract PageController getPageController();
}
