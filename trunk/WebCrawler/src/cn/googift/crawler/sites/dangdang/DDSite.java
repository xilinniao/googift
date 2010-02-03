package cn.googift.crawler.sites.dangdang;

import cn.googift.crawler.page.PageLinkIterator;
import cn.googift.crawler.page.PageParser;
import cn.googift.crawler.sites.Site;

public class DDSite extends Site {
    public DDSite() {
        super("dangdang.com");
    }

    public PageLinkIterator getPageLinkIterator() {
        return new DDPageLinkIterator();
    }

    public PageParser getPageParser() {
        return new DDPageParser(this);
    }

    public DDParameters getParameters() {
        return new DDParameters();
    }

}
