package cn.googift.crawler.sites.jingdong;

import cn.googift.crawler.sites.Site;
import cn.googift.crawler.page.PageController;

public class JDSite extends Site {
    public JDSite() {
        super("buy360.com");
    }

    public PageController getPageController() {
        return new JDPageController();
    }
}
