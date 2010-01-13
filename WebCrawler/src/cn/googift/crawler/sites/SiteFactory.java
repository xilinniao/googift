package cn.googift.crawler.sites;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import cn.googift.crawler.data.Product;
import cn.googift.crawler.page.Page;
import cn.googift.crawler.page.PageLinkIterator;
import cn.googift.crawler.page.PageParser;
import cn.googift.crawler.service.ProductService;
import cn.googift.crawler.startup.Startup;
import cn.googift.crawler.util.FileUtil;
import cn.googift.crawler.util.page.PagePoller;


public class SiteFactory {
    
    private static SiteFactory instance = null;
    
    //the absolute directory path for all site plugins
    private String pluginsHome;
    
    private ProductService productService;
    
    public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	//the site config parser to parse all site configuration
    private SiteConfigParser siteConfigParser = new SiteConfigParser();
    
    private List<SiteConfig> siteConfigs = new ArrayList<SiteConfig>();
    private Map<String, Site> sites = new HashMap<String, Site>();
    
	private SiteFactory(){}
    
    public static SiteFactory getInstance()
    {
        if (instance == null)
        {
            instance = new SiteFactory();
        }
        return instance;
    }
    
    
    public void initSiteConfigs() throws Exception 
    {
        siteConfigs.clear();
        
        File f = new File(pluginsHome);
        if (!f.exists())
        {
            throw new Exception("the plugin home" + pluginsHome + "is not existent");
        } 
        
        if (!f.isDirectory())
        {
            throw new Exception("the plugin home " + pluginsHome + "is not valid directory");
        }
        
        if (!f.canRead())
        {
            throw new Exception("you have not permisstion to read directory: " + pluginsHome );
        }
        
        
        File[] siteDirs = f.listFiles();
        SiteConfig siteConfig = null;
        for (int i=0; i<siteDirs.length; i++)
        {
            try
            {
                siteConfigParser.setSiteConfigHome(siteDirs[i].getCanonicalPath());
                siteConfigParser.parse();
                siteConfig = siteConfigParser.getSiteConfig();
                siteConfig.setSitePluginHome(siteDirs[i].getCanonicalPath());
                siteConfigs.add(siteConfig);
            }
            catch (Exception e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    
    /*
     * initialize the sites by the site configuraton information.
     * */
    public void initSites()
    {
        List<URL> lst = new ArrayList<URL>();
        Iterator<SiteConfig> iterator = siteConfigs.iterator();
        while( iterator. hasNext() )
        {
            SiteConfig siteConfig= iterator.next();
            try
            {
                lst.addAll(FileUtil.getURLList(null, 
                    new File[]{
                        new File(siteConfig.getCanonicalJarHome())}));
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        
        /*
         * Load site class dynamically
         * */
        ClassLoader classLoader = new URLClassLoader(lst.toArray(new URL[lst.size()]));
        ClassLoader currentClassLoader = Thread.currentThread().getContextClassLoader();
        try
        {
            Thread.currentThread().setContextClassLoader(classLoader);
            
            iterator = siteConfigs.iterator();
            while( iterator. hasNext() )
            {
                SiteConfig siteConfig= iterator.next();
                Class<Site> clz;
                try
                {
                    clz = (Class<Site>)classLoader.loadClass(siteConfig.getMainClass());
                    
                    Site site = clz.newInstance();
                    site.setSiteConfig(siteConfig);
                    sites.put(site.getDomain(), site);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
          
        finally
        {
            Thread.currentThread().setContextClassLoader(currentClassLoader);
        }
    }
    
    public void crawlSites()
    {
    	Iterator<Site> it = sites.values().iterator();
    	while (it.hasNext())
    	{
    		Site site = it.next();
    		new Thread(new CrawlSiteTask(site, 
    				new ProductService(Startup.getEntityManager())))
    				.start();
    		//crawlSite(it.next());
    	}
    }
    
    
    public Site getSiteByDomain(String siteDomain)
    {
        return sites.get(siteDomain);
    }
    
    public int getSitesCount()
    {
        return sites.size();
    }
    
    public static void main(String args[])
    {
        SiteFactory f = SiteFactory.getInstance();
        f.setPluginsHome("E:\\Leiw\\SitePlugins");
        try
        {
            f.initSiteConfigs();
            f.initSites();
            f.crawlSites();
            //System.out.print(f.getSites());
        }
        catch (Exception e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public String getPluginsHome()
    {
        return pluginsHome;
    }
    

    public void setPluginsHome(String pluginsHome)
    {
        this.pluginsHome = pluginsHome;
    }

    public List<SiteConfig> getSiteConfigs()
    {
        return siteConfigs;
    }

    public Map<String, Site> getSites()
    {
        return sites;
    }
    
    public void setSites(Map<String, Site> sites) {
		this.sites = sites;
	}
    
    class CrawlSiteTask implements Runnable 
    {
    	private final Site site;
    	private final ProductService productService;
    	
		public CrawlSiteTask(Site site, ProductService productService) 
		{
			this.site = site;
			this.productService = productService;
		}

		public void run() 
		{
			final PageLinkIterator linkIterator = site.getPageLinkIterator();
	        final PageParser parser = site.getPageParser();
	        
	        int count = 0;
	        
	        while (linkIterator.hasNext() && count < 100) {
	            final String link = linkIterator.next();
				try {
					Page page = PagePoller.poll(link, "gb2312");
		            final Product product = parser.parse(page);
		            if (null != product) 
		            {
		            	System.out.println("[" + count + "]   " + "parse Url() " + product.getUrl());
		                
		            	Product existProduct = productService.getProductByUrl(product.getUrl());
		                if (null != existProduct)
		                {
		                	existProduct.copyPropertiesFromProduct(product);
		                	productService.update(existProduct);
		                } 
		                else
		                {
		                	product.setGuid(UUID.randomUUID().toString());
		                	productService.create(product);
		                }
		            }
		            else
		            {
		            	System.out.println("[" + count + "]   " + " Failed " + page.getURL() );
		            }
		            
		            count++;
				} catch (Exception e) 
				{
					//make sure any exception will not break the thread.
					e.printStackTrace();
				}
				
	        }
		}
    	
    }
}