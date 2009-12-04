package cn.googift.crawler.startup;

import cn.googift.crawler.loader.ClassLoaderFactory;
import cn.googift.crawler.sites.Site;


public final class Startup {

    public Startup() {
    }
    
    @SuppressWarnings("unchecked")
    public void run() throws Exception
    {
        ClassLoader siteClassLoader = ClassLoaderFactory.createSiteClassLoader("C:/bin");
        
        //the main class should read from site.xml 
        Class<Site> clz = (Class<Site>)siteClassLoader.loadClass("cn.googift.crawler.sites.jingdong.JDSite");
        Site site = clz.newInstance();
        System.out.print(site.getDomain());
    }

    public static void main(String[] args) {
        Startup  startup = new Startup();
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
