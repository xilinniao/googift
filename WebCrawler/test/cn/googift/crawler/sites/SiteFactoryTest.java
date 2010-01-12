package cn.googift.crawler.sites;

import java.util.HashMap;
import java.util.Map;

import cn.googift.crawler.sites.jingdong.JDSite;
import cn.googift.crawler.sites.newegg.NewEggSite;
import junit.framework.TestCase;

public class SiteFactoryTest extends TestCase {
   Map<String, Site> sites;
   Site JDSite;
   Site NewEggSite;
   
   public void setUp()
   {
	   JDSite = new JDSite();
	   NewEggSite = new NewEggSite();
	   sites = new HashMap<String, Site>();
	   sites.put(JDSite.getDomain(), JDSite);
	   sites.put(NewEggSite.getDomain(), NewEggSite);
	   
   }
   
   public void testCrawlSites()
   {
	   SiteFactory.getInstance().setSites(sites);
	   SiteFactory.getInstance().crawlSites();
	   while (true)
	   {
	   }
	   
   }
	
}
