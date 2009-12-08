package cn.googift.crawler.util.page;

import cn.googift.crawler.page.Page;

import javax.imageio.ImageIO;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.MalformedURLException;
import java.awt.image.BufferedImage;

public class PagePoller {
    public static Page poll(String link, String encoding) throws IOException {
        URL url = new URL(link);
        final InputStream inputStream = url.openStream();
        InputStreamReader reader = new InputStreamReader(inputStream, encoding);
        BufferedReader br = new BufferedReader(reader);
        StringBuilder content = new StringBuilder();
        String temLine;
        while ((temLine = br.readLine()) != null) {
            content.append(temLine);
        }
        return new Page(link, content.toString());
    }

    public static BufferedImage pollImage(String link) throws IOException {
        return ImageIO.read(new URL(link));
    }
}
