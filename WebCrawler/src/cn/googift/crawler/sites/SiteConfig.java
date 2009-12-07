package cn.googift.crawler.sites;


/*
 * The class is for description the Site configuration
 * */
public class SiteConfig
{
    String name;
    String mainClass;
    String jarFileName;
    String jarFilepath;
    
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
    public String getJarFilepath()
    {
        return jarFilepath;
    }
    public void setJarFilepath(String jarFilepath)
    {
        this.jarFilepath = jarFilepath;
    }
}
