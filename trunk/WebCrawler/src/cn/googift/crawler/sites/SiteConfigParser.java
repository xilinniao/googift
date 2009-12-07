package cn.googift.crawler.sites;

import java.io.File;
import java.io.FileInputStream;

import javax.xml.parsers.SAXParser;

import org.apache.commons.digester.Digester;
import org.xml.sax.InputSource;

public class SiteConfigParser
{
    private String configFilePath; 
    private SiteConfig siteConfig;
    
    public SiteConfig getSiteConfig()
    {
        return siteConfig;
    }

    public void setSiteConfig(SiteConfig siteConfig)
    {
        this.siteConfig = siteConfig;
    }

    public SiteConfigParser(String configFilePath)
    {
        this.configFilePath = configFilePath;
    }
    
    public SiteConfig parseSiteConfig() throws Exception
    {
        Digester digester = new Digester();
        
        //ignore validating XML
        digester.setValidating( false );
        digester.push(this);
        digester.addObjectCreate("site", SiteConfig.class);
        digester.addBeanPropertySetter("site/name");
        digester.addBeanPropertySetter("site/class", "mainClass");
        digester.addBeanPropertySetter("site/jar","jarFileName");
        digester.addSetNext("site", "setSiteConfig");
        
        InputSource inputSource = new InputSource(new FileInputStream(new File(configFilePath)));
        inputSource.setEncoding("utf-8");
        SiteConfigParser parser = (SiteConfigParser) digester.parse(inputSource);
        return parser.getSiteConfig();
        
    }

    public String getConfigFilePath()
    {
        return configFilePath;
    }

    public void setConfigFilePath(String configFilePath)
    {
        this.configFilePath = configFilePath;
    }
    
    public static void  main(String[] args)
    {
        SiteConfigParser parser = new SiteConfigParser("D://test1.xml");
        try
        {
            SiteConfig siteConfig = parser.parseSiteConfig();
            System.out.println(siteConfig);
            System.out.println(siteConfig.getName());
            System.out.println(siteConfig.getJarFileName());
                
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
}
