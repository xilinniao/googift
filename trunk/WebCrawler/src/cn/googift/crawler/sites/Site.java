package cn.googift.crawler.sites;

import cn.googift.crawler.page.PageLinkIterator;
import cn.googift.crawler.page.PageParser;

public abstract class Site {
    protected SiteConfig siteConfig;
    protected final String domain;

    protected Site(String domain) {
        this.domain = domain;
    }

    public String getDomain() {
        return domain;
    }

    public abstract PageLinkIterator getPageLinkIterator();

    public abstract PageParser getPageParser();
    
    public SiteConfig getSiteConfig()
    {
        return siteConfig;
    }

    public void setSiteConfig(SiteConfig siteConfig)
    {
        this.siteConfig = siteConfig;
    }
    
    public String toString()
    {
        return "Site Domain:" + this.domain + "\n" + siteConfig.toString(); 
    }
}
