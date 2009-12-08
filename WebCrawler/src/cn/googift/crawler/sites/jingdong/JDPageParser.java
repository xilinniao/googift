package cn.googift.crawler.sites.jingdong;

import cn.googift.crawler.data.Product;
import cn.googift.crawler.page.Page;
import cn.googift.crawler.page.PageParser;
import cn.googift.crawler.util.parser.OneGroupContentParser;
import cn.googift.crawler.util.parser.PriceHandler;
import cn.googift.crawler.util.page.PagePoller;
import cn.googift.crawler.sites.jingdong.recognize.JDPriceParser;
import cn.googift.recognition.Recognizer;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class JDPageParser extends PageParser {
    private final JDSite site;

    public JDPageParser(JDSite site) {
        this.site = site;
    }

    public Product parse(Page page) {
        if (!isValidPage(page))
            return null;
        final String pageContent = page.getContent();
        final JDParameters jdParameters = site.getJDParameters();
        final String name = OneGroupContentParser.pickContent(jdParameters.getNamePattern(), pageContent);
        if (null == name) return null;
        final Product product = new Product(page.getURL(), name);
        final String mps = OneGroupContentParser.pickContent(jdParameters.getMarketPricePattern(), pageContent);
        if (null != mps) {
            product.setMarketPrice(PriceHandler.parsePriceNumber(mps));
        }
        final String picLink = OneGroupContentParser.pickContent(jdParameters.getPricePattern(), pageContent);
        product.setPrice(PriceHandler.parsePriceNumber(JDPriceParser.parsePrice(picLink)));
        return product;
    }

    private boolean isValidPage(Page page) {
        return !page.getURL().endsWith(JDSite.DOMAIN);
    }

}
