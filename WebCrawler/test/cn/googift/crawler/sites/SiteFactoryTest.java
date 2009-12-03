package cn.googift.crawler.sites;

import cn.googift.crawler.sites.jingdong.JDSite;
import junit.framework.TestCase;

public class SiteFactoryTest extends TestCase {
    public void testGetSiteIterator() {
        final SiteIterator siteIterator = SiteFactory.getSiteIterator();
        assertTrue(siteIterator.hasNext());
        final Site site = siteIterator.next();
        assertNotNull(site);
        assertTrue(site instanceof JDSite);
    }
}
