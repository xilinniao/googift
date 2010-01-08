package cn.googift.crawler.startup;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import cn.googift.crawler.service.ProductService;
import cn.googift.crawler.sites.SiteFactory;


public final class Startup {

    private String pluginsHome;
    private static EntityManagerFactory emf;
    private static EntityManager em;
    private ProductService ps;
    
    public Startup(String pluginsHome) {
        this.pluginsHome = pluginsHome;
    }
    
    public static EntityManager getEntityManager()
    {
        if (emf == null)
        {
        	emf = Persistence.createEntityManagerFactory("crawler");
        }
        if (em == null)
        {
        	em = emf.createEntityManager();
        }
        return em;
    }
    
    public void run() throws Exception
    {
        SiteFactory f = SiteFactory.getInstance();
        f.setPluginsHome(pluginsHome);
        f.initSiteConfigs();
        f.initSites();
        f.setProductService(new ProductService(em));
        f.crawlSites();
        //System.out.print(f.getSites().values());
    }

    
    public static void main(String[] args) {
        
//        if (args.length < 1)
//        {
//            System.out.println("Please set your plugins home directory:\n\n");
//            System.exit(0);
//        }
        Startup  startup = new Startup("c:\\wamp\\www\\googift\\WebCrawler\\plugins");
        try
        {
            startup.run();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
