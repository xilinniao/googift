package cn.googift.crawler.sites;

import cn.googift.crawler.data.Product;
import cn.googift.crawler.page.Page;
import cn.googift.crawler.page.PageLinkIterator;
import cn.googift.crawler.page.PageParser;
import cn.googift.crawler.sites.dangdang.DDSite;
import cn.googift.crawler.util.page.PagePoller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class TestMain {
    public static void main(String arg[]) throws IOException {
        Site site = new DDSite();
        final PageLinkIterator linkIterator = site.getPageLinkIterator();
        final PageParser parser = site.getPageParser();
        int count = 0;
        while (linkIterator.hasNext() && count < 100) {
            final String link = linkIterator.next();
            final Page page = PagePoller.poll(link, "gb2312");
            final Product product = parser.parse(page);
            if (null != product) {
                System.out.println("product.getUrl() = " + product.getUrl());
                System.out.println("product.getName() = " + product.getName());
                System.out.println("product.getMarketPrice() = " + product.getMarketPrice());
                System.out.println("product.getPrice() = " + product.getDiscountPrice());
                Map<String,String> picLinks = product.getPicLinks();
                if(null != picLinks) {
                    for(Map.Entry<String, String> e : picLinks.entrySet()) {
                        System.out.print(e.getKey() + " " + e.getValue() + "; ");
                    }
                    System.out.println("");
                }
                System.out.println("product.getDescription() = " + product.getDescription());
                List<String> stringList = product.getCategories();
                if(null != stringList) {
                    System.out.print("product.getCategories() = ");
                    for(String s : stringList) {
                        System.out.print(s + "->");
                    }
                    System.out.println("");
                }
            }
            count++;
            System.out.println("count = " + count);
        }
    }
}
