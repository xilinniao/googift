package cn.googift.crawler.sites.newegg;

import cn.googift.crawler.data.Product;
import cn.googift.crawler.page.Page;
import cn.googift.crawler.page.PageParser;
import cn.googift.crawler.sites.newegg.recognize.PriceParser;
import cn.googift.crawler.util.parser.HTMLHelper;
import cn.googift.crawler.util.parser.OneGroupContentParser;
import cn.googift.crawler.util.parser.PriceHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewEggPageParser extends PageParser {
    private final NewEggSite site;

    public NewEggPageParser(NewEggSite site) {
        this.site = site;
    }

    @Override
    public Product parse(Page page) {
        if (!isValidPage(page))
            return null;
        final String pageContent = page.getContent();
        final NewEggParameters parameters = site.getNewEggParameters();
        String name = OneGroupContentParser.pickContent(parameters.getNamePattern(), pageContent);
        if (null == name) return null;
        if(name.endsWith(" - 新蛋中国")) name = name.substring(0, name.length() - " - 新蛋中国".length());
        final Product product = new Product(page.getURL(), name);

        final String mps = OneGroupContentParser.pickContent(parameters.getMarketPricePattern(), pageContent);
        if (null != mps) {
            product.setMarketPrice(PriceHandler.parsePriceNumber(mps));
        }
        final String picLink = OneGroupContentParser.pickContent(parameters.getPricePattern(), pageContent);
        product.setDiscountPrice(PriceHandler.parsePriceNumber(PriceParser.parsePrice("http://www.newegg.com.cn/"+picLink)));
        product.setPicLinks(parsePicLinks(parameters, pageContent));
        List<String> divs = HTMLHelper.getTagContentWithAttribute(pageContent, "div", "id", "tabCot_product");
        if(null != divs && divs.size() > 0) product.setDescription(HTMLHelper.htmlToString(divs.get(0)));

        return product;
    }

    private boolean isValidPage(Page page) {
        return !page.getContent().contains("<body id=\"Search\">");
    }

    private Map<String, String> parsePicLinks(NewEggParameters jdP, String pageContent) {
        List<String> littlePicLinks = OneGroupContentParser.pickMultipleOccurs(jdP.getPictureLittleImageLinkPattern(), pageContent);
        if (null != littlePicLinks) {
            Map<String, String> picLinks = new HashMap<String, String>();
            for (String link : littlePicLinks) {
                picLinks.put(link, link.replace("P40", "mpic"));
            }
            return picLinks;
        }
        return null;
    }
}
