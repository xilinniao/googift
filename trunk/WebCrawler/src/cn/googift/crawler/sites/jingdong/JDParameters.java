package cn.googift.crawler.sites.jingdong;

import java.util.Properties;
import java.io.IOException;

public class JDParameters {
    private static Properties parameterProp = new Properties();

    static {
        try {
            parameterProp.load(JDParameters.class.getResourceAsStream("/cn/googift/crawler/sites/jingdong/parameters.properties"));
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
        return parameterProp.getProperty("priceLinkPattern");
    }

    public String getLittleImageDivPattern() {
        return parameterProp.getProperty("productLittleImageDivPattern");
    }

    public String getProductNamePatternFromLittleImageDiv() {
        return parameterProp.getProperty("productNamePatternFromLittleImageDiv");
    }

    public String getProductImageLink(String imageName) {
        return parameterProp.getProperty("productImagePrefix") + imageName;
    }

    public String getProductLittleImageLink(String imageName) {
        return parameterProp.getProperty("productLittleImagePrefix") + imageName;
    }

    public String getPositionDivPattern() {
        return parameterProp.getProperty("positionDivPattern");
    }

    public String getAHrefContentPattern() {
        return parameterProp.getProperty("ahrefContentPattern");
    }
}
