package cn.googift.crawler.sites;

import java.util.ArrayList;
import java.util.List;

import cn.googift.crawler.sites.jingdong.JDSite;

public class SiteFactory {
    public static SiteIterator getSiteIterator() {
    	List<Site> sites = new ArrayList<Site>();
    	sites.add(new JDSite());
        return new SiteIterator(sites);
    }
}
