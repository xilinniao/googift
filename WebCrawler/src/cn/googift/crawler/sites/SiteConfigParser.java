package cn.googift.crawler.sites;

import java.io.File;

import org.apache.commons.digester.Digester;

import cn.googift.crawler.Constans;

public class SiteConfigParser implements Constans
{
    private String siteConfigHome;
    private SiteConfig siteConfig;

    public SiteConfigParser()
    {
    }
    
    public SiteConfig getSiteConfig() throws Exception
    {
        return siteConfig;
    }

    public void setSiteConfig(SiteConfig siteConfig)
    {
        this.siteConfig = siteConfig;
    }
    
    public String getSiteConfigHome()
    {
        return siteConfigHome;
    }

    public void setSiteConfigHome(String siteConfigHome)
    {
        this.siteConfigHome = siteConfigHome;
    }
    
    public void parse() throws Exception
    {
        Digester digester = new Digester();
        
        //ignore validating XML
        digester.setValidating( false );
        digester.push(this);
        
        digester.addObjectCreate("site", SiteConfig.class);
        digester.addBeanPropertySetter("site/name");
        digester.addBeanPropertySetter("site/class", "mainClass");
        digester.addBeanPropertySetter("site/jar","jarFileName");
        digester.addBeanPropertySetter("site/jarHome","jarFilePath");
        digester.addSetNext("site", "setSiteConfig");
        
        File f = new File(siteConfigHome + File.separator + siteConfigFileName); 
        
        if (!f.exists())
        {
            throw new Exception ("the site.xml is not existent in " + siteConfigHome);        }
        
        digester.parse(f);
    }



}
