package cn.googift.crawler.sites.newegg;

import cn.googift.crawler.data.Product;
import cn.googift.crawler.page.Page;
import cn.googift.crawler.page.PageParser;
import cn.googift.crawler.sites.jingdong.recognize.JDPriceParser;
import cn.googift.crawler.sites.newegg.recognize.PriceParser;
import cn.googift.crawler.util.parser.OneGroupContentParser;
import cn.googift.crawler.util.parser.PriceHandler;

import java.util.ArrayList;
import java.util.List;

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
        final String name = OneGroupContentParser.pickContent(parameters.getNamePattern(), pageContent);
        if (null == name) return null;
        final Product product = new Product(page.getURL(), name);

        final String mps = OneGroupContentParser.pickContent(parameters.getMarketPricePattern(), pageContent);
        if (null != mps) {
            product.setMarketPrice(PriceHandler.parsePriceNumber(mps));
        }
        final String picLink = OneGroupContentParser.pickContent(parameters.getPricePattern(), pageContent);
        product.setPrice(PriceHandler.parsePriceNumber(PriceParser.parsePrice("http://www.newegg.com.cn/"+picLink)));
        product.setPicLinks(parsePicLinks(parameters, pageContent));

//        List<String> descriptionDivs = HTMLHelper.getTagContentWithAttribute(pageContent, "div", "id", "EFF_PINFO_Con_0");
//        if(null != descriptionDivs && descriptionDivs.size() > 0) {
//            product.setDescription(descriptionDivs.get(0));
//        }
//
//        String divContent = OneGroupContentParser.pickContent(parameters.getPositionDivPattern(), pageContent);
//        if(null != divContent) {
//            List<String> categories = OneGroupContentParser.pickMultipleOccurs(parameters.getAHrefContentPattern(), divContent);
//            if(null != categories && categories.size() > 0) product.setCategories(categories);
//        }

        return product;
    }

    private boolean isValidPage(Page page) {
        return !page.getContent().contains("<body id=\"Search\">");
    }

    private List<String> parsePicLinks(NewEggParameters jdP, String pageContent) {
        List<String> littlePicLinks = OneGroupContentParser.pickMultipleOccurs(jdP.getPictureLittleImageLinkPattern(), pageContent);
        if (null != littlePicLinks) {
            List<String> picLinks = new ArrayList<String>(littlePicLinks.size());
            for (String link : littlePicLinks) {
                picLinks.add(link.replace("P40", "mpic"));
            }
            return picLinks;
        }
        return null;
    }
}
