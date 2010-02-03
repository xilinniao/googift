package cn.googift.crawler.sites.jingdong;

import cn.googift.crawler.data.Product;
import cn.googift.crawler.page.Page;
import cn.googift.crawler.page.PageLinkIterator;
import cn.googift.crawler.page.PageParser;
import cn.googift.crawler.sites.Site;
import cn.googift.crawler.util.page.NumberRangePageLinkIterator;
import cn.googift.crawler.util.page.PagePoller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class JDSite extends Site {
    private static final JDParameters param = new JDParameters();
    static final String DOMAIN = "360buy.com";

    public JDSite() {
        super(DOMAIN);
    }

    private static final int startId = 187581;
    private static final int endId = 199999;
    private static final String linkPrefix = "http://www.360buy.com/product/";
    private static final String linkSuffix = ".html";

    public PageLinkIterator getPageLinkIterator() {
        return new NumberRangePageLinkIterator(startId, endId, linkPrefix, linkSuffix);
    }


    public PageParser getPageParser() {
        return new JDPageParser(this);
    }

    public JDParameters getJDParameters() {
        return param;
    }

}
