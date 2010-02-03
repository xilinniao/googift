package cn.googift.crawler.sites.dangdang;

import cn.googift.crawler.sites.jingdong.JDParameters;

import java.util.Properties;
import java.io.IOException;

public class DDParameters {
    private static Properties parameterProp = new Properties();

    static {
        try {
            parameterProp.load(JDParameters.class.getResourceAsStream("/cn/googift/crawler/sites/dangdang/parameters.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getNamePattern() {
        return parameterProp.getProperty("namePattern");
    }

    public String getMarketPricePattern() {
        return parameterProp.getProperty("marketPricePattern");
    }

    public String getPricePattern() {
        return parameterProp.getProperty("pricePattern");
    }

    public String getProductImageLinkPattern() {
        return parameterProp.getProperty("productImageLinkPattern");
    }

}