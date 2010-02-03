package cn.googift.crawler.sites.dangdang;

import cn.googift.crawler.page.PageLinkIterator;

public class DDPageLinkIterator implements PageLinkIterator {
    private static final String prefix = "http://product.dangdang.com/product.aspx?product_id=";
    private static final String[] middles = new String[]{"20", "400", "401"};
    private static final int min = 100000;
    private static final int max = 999999;
    private int middleArrayIndex = 0;
    private int numberIndex = min;

    public boolean hasNext() {
        boolean noNext = (middleArrayIndex == middles.length - 1 && numberIndex == max);
        return !noNext;
    }

    public String next() {
        numberIndex++;
        if (numberIndex > max) {
            middleArrayIndex++;
            numberIndex = min;
        }
        return prefix + middles[middleArrayIndex] + numberIndex;
    }

    public void remove() {

    }
}
