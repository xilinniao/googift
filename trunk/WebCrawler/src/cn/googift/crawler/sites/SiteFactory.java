package cn.googift.crawler.sites;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import cn.googift.crawler.util.*;


public class SiteFactory {
    
    private static SiteFactory instance = null;
    
    //the absolute directory path for all site plugins
    private String pluginsHome;
    
    
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
            siteConfigParser.setSiteConfigHome(siteDirs[i].getCanonicalPath());
            siteConfigParser.parse();
            siteConfig = siteConfigParser.getSiteConfig();
            siteConfig.setSitePluginHome(siteDirs[i].getCanonicalPath());
            siteConfigs.add(siteConfig);
        }
    }
    
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
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        
        ClassLoader classLoader = new URLClassLoader(lst.toArray(new URL[lst.size()]));
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
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
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
        f.setPluginsHome("D:\\SitePlugins");
        try
        {
            f.initSiteConfigs();
            f.initSites();
            System.out.print(f.getSites());
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
}