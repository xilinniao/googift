package cn.googift.crawler.sites;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import cn.googift.crawler.service.ProductService;
import cn.googift.crawler.sites.jingdong.JDSite;
import cn.googift.crawler.sites.newegg.NewEggSite;
import junit.framework.TestCase;

public class SiteFactoryTest extends TestCase {
	
   EntityManagerFactory emf;
   EntityManager em;
   ProductService ps;
	
   Map<String, Site> sites;
   Site JDSite;
   Site NewEggSite;
   
   public void setUp()
   {
	    emf = Persistence.createEntityManagerFactory("crawler");
        em =  emf.createEntityManager();
        ps = new ProductService(em);
        
	   JDSite = new JDSite();
	   NewEggSite = new NewEggSite();
	   sites = new HashMap<String, Site>();
	   sites.put(JDSite.getDomain(), JDSite);
	   sites.put(NewEggSite.getDomain(), NewEggSite);
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
