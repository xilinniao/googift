package cn.googift.crawler.sites;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import cn.googift.crawler.service.ProductService;
import cn.googift.crawler.sites.jingdong.JDSite;
import cn.googift.crawler.sites.newegg.NewEggSite;
import cn.googift.crawler.sites.dangdang.DDSite;
import junit.framework.TestCase;

public class SiteFactoryTest extends TestCase {
	
   EntityManagerFactory emf;
   EntityManager em;
   ProductService ps;
	
   Map<String, Site> sites;
   Site JDSite;
   Site NewEggSite;
   Site DDSite;
   
   public void setUp()
   {
	    emf = Persistence.createEntityManagerFactory("crawler");
        em =  emf.createEntityManager();
        ps = new ProductService();
        
	   JDSite = new JDSite();
	   NewEggSite = new NewEggSite();
	   DDSite = new DDSite();
	   sites = new HashMap<String, Site>();
	   sites.put(JDSite.getDomain(), JDSite);
	   sites.put(NewEggSite.getDomain(), NewEggSite);
	   sites.put(DDSite.getDomain(), DDSite);
   }
	

	protected void tearDown() throws Exception {
		em.close();
		emf.close();
	}
   
   public void testCrawlSites()
   {
	   //SiteFactory.getInstance().setProductService(ps);
	   SiteFactory.getInstance().setSites(sites);
	   SiteFactory.getInstance().crawlSites();
	   while (true)
	   {
	   }
	   
   }
	
}
