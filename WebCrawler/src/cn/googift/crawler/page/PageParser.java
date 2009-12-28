package cn.googift.crawler.page;

import cn.googift.crawler.data.Product;



public abstract class PageParser {
    public abstract Product parse(Page page);
}
