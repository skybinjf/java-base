package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * UrlDemo
 *
 * @author Sky
 * @date 2021-01-16 17:12.
 */
public class UrlDemo {

    public static void main(String[] args) throws IOException {
        URL imooc = new URL("http://www.imooc.com");
        URL url = new URL(imooc, "/index.html?username=tom#test");
        System.out.println(url.getProtocol());
        System.out.println(url.getHost());
        System.out.println(url.getPort());
        System.out.println(url.getPath());
        System.out.println(url.getFile());
        System.out.println(url.getRef());
        System.out.println(url.getQuery());

        // 使用 URL 读取网页内容
        URL baidu = new URL("http://www.baidu.com");
        InputStream is = baidu.openStream();
        InputStreamReader isr = new InputStreamReader(is,



                "utf-8");
        BufferedReader br = new BufferedReader(isr);
        String data = br.readLine();
        while (data != null) {
            System.out.println(data);
            data = br.readLine();
        }
        br.close();
        isr.close();
        is.close();
    }

}
