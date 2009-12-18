package cn.googift.crawler.startup;

import cn.googift.crawler.sites.SiteFactory;


public final class Startup {

    private String pluginsHome;
    public Startup(String pluginsHome) {
        this.pluginsHome = pluginsHome;
    }
    
    public void run() throws Exception
    {
        SiteFactory f = SiteFactory.getInstance();
        f.setPluginsHome(pluginsHome);
        f.initSiteConfigs();
        f.initSites();
        System.out.print(f.getSites().values());
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
