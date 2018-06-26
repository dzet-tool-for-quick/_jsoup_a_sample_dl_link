import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class DownloadLink {

    public static void main(String[] args) throws IOException {

        String url = "https://github.com/dzet-pdf/_javascript_book_dl";
        printDownloadLink(url);

    }

    private static void printDownloadLink(String url) throws IOException {
        Document doc = Jsoup.connect(url + "/tree/master/file").get();
        Elements aEles = doc.select("#js-repo-pjax-container > div.container.new-discussion-timeline.experiment-repo-nav > div.repository-content > div.file-wrap > table > tbody:nth-child(2) > tr:gt(0) > td:eq(1) > span > a");

        for (Element aEle : aEles) {
            String title = aEle.attr("title");
            String href = "https://github.com" + aEle.attr("href");
            int length = title.length();
            boolean exitPartString = title.contains("part");

            System.out.print("> [");
            if (exitPartString) {
                System.out.print(title.substring(length - 9, length - 4));
            }else {
                System.out.print("part1");
            }
            System.out.print("](");
            System.out.print(href);
            System.out.println(")");
        }
    }
}
