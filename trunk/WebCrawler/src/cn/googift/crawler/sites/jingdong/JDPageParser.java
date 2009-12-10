package cn.googift.crawler.sites.jingdong;

import cn.googift.crawler.data.Product;
import cn.googift.crawler.page.Page;
import cn.googift.crawler.page.PageParser;
import cn.googift.crawler.sites.jingdong.recognize.JDPriceParser;
import cn.googift.crawler.util.parser.OneGroupContentParser;
import cn.googift.crawler.util.parser.PriceHandler;

import java.util.ArrayList;
import java.util.List;

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
        product.setPicLinks(parsePicLinks(jdParameters, pageContent));
        return product;
    }

    private boolean isValidPage(Page page) {
        return !page.getURL().endsWith(JDSite.DOMAIN);
    }

    private List<String> parsePicLinks(JDParameters jdP, String pageContent) {
        String div = OneGroupContentParser.pickContent(jdP.getLittleImageDivPattern(), pageContent);
        if (null == div) return null;
        List<String> imgNameList = OneGroupContentParser.pickMultipleOccurs(jdP.getProductNamePatternFromLittleImageDiv(), div);
        if (null != imgNameList) {
            List<String> picLinks = new ArrayList<String>(imgNameList.size());
            for (String name : imgNameList) {
                picLinks.add(jdP.getProductImageLink(name));
            }
            return picLinks;
        }
        return null;
    }

}
