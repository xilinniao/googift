package cn.googift.crawler.sites.newegg;

import cn.googift.crawler.page.PageLinkIterator;
import cn.googift.crawler.page.PageParser;
import cn.googift.crawler.sites.Site;

public class NewEggSite extends Site {
    static final String DOMAIN = "newegg.com.cn";
    private static final NewEggParameters param = new NewEggParameters();

    public NewEggSite() {
        super(DOMAIN);
    }

    @Override
    public PageLinkIterator getPageLinkIterator() {
        return new NewEggPageIterator();
    }

    @Override
    public PageParser getPageParser() {
        return new NewEggPageParser(this);
    }

    public NewEggParameters getNewEggParameters() {
        return param;
    }

}
