package cn.googift.crawler.sites.dangdang;

import cn.googift.crawler.data.Product;
import cn.googift.crawler.page.Page;
import cn.googift.crawler.page.PageParser;
import cn.googift.crawler.util.parser.OneGroupContentParser;
import cn.googift.crawler.util.parser.RegexMatcher;
import cn.googift.crawler.util.parser.PriceHandler;
import cn.googift.crawler.util.parser.HTMLHelper;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class DDPageParser extends PageParser {
    private final DDSite site;

    public DDPageParser(DDSite site) {
        this.site = site;
    }
    public Product parse(Page page) {
        String url = page.getURL();
        String content = page.getContent();
        final DDParameters ddParameters = site.getParameters();
        final String name = OneGroupContentParser.pickContent(ddParameters.getNamePattern(), content);
        if(null == name) return null;
        Product p = new Product(url,name);
        String marketPrice = OneGroupContentParser.pickContent(ddParameters.getMarketPricePattern(), content);
        if(null != marketPrice) {
            p.setMarketPrice(PriceHandler.parsePriceNumber(marketPrice));
        }
        String price = OneGroupContentParser.pickContent(ddParameters.getPricePattern(), content);
        if(null != price) {
            p.setDiscountPrice(PriceHandler.parsePriceNumber(price));
        }
        List<String[]> images = new RegexMatcher(ddParameters.getProductImageLinkPattern(), false, true).matchAll(content);
        if(null != images && images.size() > 0) {
            Map<String, String> linkMap = new HashMap<String, String>(images.size());
            for(String[] sa : images) {
                if(sa.length > 0 ) {
                    if(!linkMap.containsKey(sa[0])) {
                        linkMap.put(sa[0], sa[0].replace("_o.", "_b."));
                    }
                }
            }
            p.setPicLinks(linkMap);
        }
        List<String> description = HTMLHelper.getTagContentWithAttribute(content, "div", "class", "mall_goods_foursort_body1");
        if(null == description || description.size() == 0) description = HTMLHelper.getTagContentWithAttribute(content, "div", "class", "book_right");
        if(null != description && description.size() > 0) p.setDescription(HTMLHelper.htmlToString(description.get(0)));
        return p;
    }
}
