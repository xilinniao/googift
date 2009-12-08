package cn.googift.crawler.sites.jingdong;

import java.util.Properties;
import java.io.IOException;

public class JDParameters {
   private static Properties paramProp = new Properties();
    static {
        try {
            paramProp.load(JDParameters.class.getResourceAsStream("/cn/googift/crawler/sites/jingdong/parameters.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getNamePattern() {
        return paramProp.getProperty("namePattern");
    }

    public String getMarketPricePattern() {
        return paramProp.getProperty("marketPricePattern");
    }

    public String getPricePattern() {
        return paramProp.getProperty("priceLinkPattern");
    }
}
