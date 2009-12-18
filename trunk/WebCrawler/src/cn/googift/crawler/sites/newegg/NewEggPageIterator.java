package cn.googift.crawler.sites.newegg;

import cn.googift.crawler.page.PageLinkIterator;

import java.text.NumberFormat;

public class NewEggPageIterator implements PageLinkIterator {
    private final static String prefix = "http://www.newegg.com.cn/Product/";
    // middle: <aa>-C<bb>-<ccc>. aa: 01-99; bb: 01-99; ccc: 001-999
    private final static String suffix = ".htm";
    private int aa = 24;
    private int bb = 12;
    private int ccc = 202;

    private static final NumberFormat twoNumber = NumberFormat.getNumberInstance();
    private static final NumberFormat threeNumber = NumberFormat.getNumberInstance();

    static {
        twoNumber.setMaximumFractionDigits(0);
        twoNumber.setMinimumIntegerDigits(2);
        threeNumber.setMaximumFractionDigits(0);
        threeNumber.setMinimumIntegerDigits(3);
    }

    public boolean hasNext() {
        return aa <= 99;
    }

    public String next() {
        ccc++;
        if (ccc > 999) {
            bb++;
            ccc = 0;
        }

        if (bb > 99) {
            aa++;
            bb = 0;
        }

        return prefix + twoNumber.format(aa) + "-c" + twoNumber.format(bb) + "-" + threeNumber.format(ccc) + suffix;
    }

    public void remove() {

    }
}
