package cn.googift.crawler.loader;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

public class ClassLoaderFactory {
    
    private ClassLoaderFactory() 
    {
    }

    public static List<URL> getURLList(File[] classesDirs, File[] libDirs) throws Exception
    {
        List<URL> urlList = new ArrayList<URL>();
        
        if (classesDirs != null) {
            for (int i = 0; i < classesDirs.length; i++) 
            {
                File file = classesDirs[i];
                if(file == null){
                    continue;
                }
                
                if (!file.exists() || !file.canRead()) {
                    continue;
                }
                
                URL url = null;
                if (file.isDirectory()) 
                {
                    url = file.toURI().toURL();
                    urlList.add(url);
                }
                else 
                {
                    if (file.getCanonicalPath().toLowerCase().endsWith(".jar")) {
                        url = file.toURI().toURL();
                        urlList.add(url);
                    }
                }
            }
        }
        
        if (libDirs != null) {
            for (int i = 0; i < libDirs.length; i++) 
            {
                File directory = libDirs[i];
                if(directory == null)
                {
                    continue;
                }
                if (!directory.isDirectory() || !directory.exists() 
                        || !directory.canRead()) 
                {
                    continue;
                }
                String filenames[] = directory.list();
                for (int j = 0; j < filenames.length; j++) 
                {
                    String filename = filenames[j].toLowerCase();
                    if (!filename.toLowerCase().endsWith(".jar")) 
                    {
                        continue;
                    }
                    File file = new File(directory, filenames[j]);
                    URL url = file.toURI().toURL();
                    urlList.add(url);
                }
            }
        }
        
        return urlList;
    }
    
    public static URL[] getURLArray(File[] classesDirs, File[] libDirs) throws Exception
    {
        List<URL> urlList = getURLList(classesDirs, libDirs);
        return urlList.toArray(new URL[urlList.size()]);
    }
    
    /**
     * Create the site class loader
     * @param sitePluginDir String - absolute path to the special site plugin jar directory 
     *   such as xxx/360.buy.com directory which including site.xml, 360.buy.com.jar   
     * @return ClassLoader
     * @throws Exception
     */
    public static ClassLoader createSiteClassLoader(String sitePluginDir) throws Exception {
        URL[] urlArray = getURLArray(null, new File[]{new File(sitePluginDir)});
        ClassLoader classLoader = new URLClassLoader(urlArray);
        return classLoader;
    }

}
