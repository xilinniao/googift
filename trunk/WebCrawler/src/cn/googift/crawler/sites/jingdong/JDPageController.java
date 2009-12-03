package cn.googift.crawler.sites.jingdong;

import cn.googift.crawler.page.PageController;
import cn.googift.crawler.page.PageLinkIterator;
import cn.googift.crawler.page.Page;
import cn.googift.crawler.util.NumberRangePageLinkIterator;

public class JDPageController implements PageController {
    private static final int startId = 100000;
    private static final int endId = 199999;
    private static final String linkPrefix = "http://www.360buy.com/product/";
    private static final String linkSufffix = ".html";

    public PageLinkIterator getPageLinkIterator() {
        return new NumberRangePageLinkIterator(startId, endId, linkPrefix, linkSufffix);
    }

    public boolean isValid(Page page) {
        return false; 
    }
}
