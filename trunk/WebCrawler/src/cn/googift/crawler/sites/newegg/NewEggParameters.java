package cn.googift.crawler.sites.newegg;

import java.io.IOException;
import java.util.Properties;

public class NewEggParameters {
    private static Properties parameterProp = new Properties();

    static {
        try {
            parameterProp.load(NewEggParameters.class.getResourceAsStream("/cn/googift/crawler/sites/newegg/parameters.properties"));
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

    public String getPictureLittleImageLinkPattern() {
        return parameterProp.getProperty("pictureLittleImageLinkPattern");
    }

    public String getPositionDivPattern() {
        return parameterProp.getProperty("positionDivPattern");
    }

    public String getAHrefContentPattern() {
        return parameterProp.getProperty("ahrefContentPattern");
    }
}