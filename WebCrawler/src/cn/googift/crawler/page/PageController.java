package cn.googift.crawler.page;

public interface PageController {
    PageLinkIterator getPageLinkIterator();
    boolean isValid(Page page);
}
