package cn.googift.crawler.sites.jingdong;

import cn.googift.crawler.page.PageLinkIterator;
import cn.googift.crawler.page.PageParser;
import cn.googift.crawler.sites.Site;
import cn.googift.crawler.util.page.NumberRangePageLinkIterator;

public class JDSite extends Site {
    public JDSite() {
        super("buy360.com");
    }

    private static final int startId = 100000;
    private static final int endId = 199999;
    private static final String linkPrefix = "http://www.360buy.com/product/";
    private static final String linkSufffix = ".html";

    public PageLinkIterator getPageLinkIterator() {
        return new NumberRangePageLinkIterator(startId, endId, linkPrefix, linkSufffix);
    }


    public PageParser getPageParser() {
        return null;
    }
}
