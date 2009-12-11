package cn.googift.crawler.sites;



/*
 * The class is for description the Site configuration
 * */
public class SiteConfig
{
    private String sitePluginHome;
    
    private String name;
    private String mainClass;
    private String jarFileName;
    private String jarFileHome;
    
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public String getMainClass()
    {
        return mainClass;
    }
    public void setMainClass(String mainClass)
    {
        this.mainClass = mainClass;
    }
    public String getJarFileName()
    {
        return jarFileName;
    }
    public void setJarFileName(String jarFileName)
    {
        this.jarFileName = jarFileName;
    }

    
    public String getJarFileHome()
    {
        return jarFileHome;
    }
    
    public void setJarFileHome(String jarFileHome)
    {
        this.jarFileHome = jarFileHome;
    }
    
    public String getCanonicalJarHome()
    {
        
        if (null == jarFileHome || jarFileHome.length()==0)
        {
            return sitePluginHome;   
        }
        return jarFileHome; 
    }
    
    public String toString()
    {
         return "Site Name:" + this.name + "\n"
             +  "Site Class:" + this.mainClass + "\n"
             +  "Site Jar home:" + this.getCanonicalJarHome() + "\n";
    }
    public String getSitePluginHome()
    {
        return sitePluginHome;
    }
    public void setSitePluginHome(String sitePluginHome)
    {
        this.sitePluginHome = sitePluginHome;
    }
}
