package cn.googift.crawler.sites;

import cn.googift.crawler.util.AppConfig;

import java.util.ArrayList;
import java.util.List;

public class SiteFactory {
    public static SiteIterator getSiteIterator() {
        String configFile = "/cn/googift/crawler/config/config.xml";
        AppConfig appConfig = new AppConfig(SiteFactory.class.getResource(configFile).getPath());
        final List list = appConfig.getList("sites.site[@class]");

        if (null == list || list.size() == 0) {
            return null;
        }

        List<Site> sites = new ArrayList<Site>(list.size());
        for (Object o : list) {
            if (o instanceof String) {
                try {
                    final Class<?> aClass = Class.forName((String) o);
                    final Object o1 = aClass.newInstance();
                    if (o1 instanceof Site) sites.add((Site) o1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return new SiteIterator(sites);
    }
}
