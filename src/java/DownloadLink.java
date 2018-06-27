import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class DownloadLink {

    public static void main(String[] args) throws IOException, InterruptedException {

        String url = "https://github.com/dzet-pdf/_javascript_book_dl";
        printDownloadLink(url);

    }

    private static void printDownloadLink(String url) throws IOException, InterruptedException {
        int reqCnt = 0;
        String reqUrl = url + "/tree/master/file";
        Document doc;
        Elements aEles;

        do {
            doc = Jsoup.connect(reqUrl).get();
//            String html = doc.html();
//            System.out.println(html);
            aEles = doc.select("#js-repo-pjax-container " +
                    "> div.container.new-discussion-timeline.experiment-repo-nav " +
                    "> div.repository-content " +
                    "> div.file-wrap " +
                    "> table " +
                    "> tbody:nth-child(2) " +
                    "> tr:gt(0) " +
                    "> td:eq(1) " +
                    "> span " +
                    "> a");

            reqCnt = reqCnt + 1;
            Thread.sleep(2000);

        }while (aEles.size() == 0); // 防止获取失败

        System.out.println("aEles.size()="+aEles.size()+" reqCnt="+reqCnt+" repeatCount=" + (reqCnt-1));
        System.out.println();

        for (Element aEle : aEles) {
            String title = aEle.attr("title");
            String href = "https://github.com" + aEle.attr("href");
            int length = title.length();
            boolean exitPartString = title.contains("part");

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("> [");
            if (exitPartString) {
                stringBuilder.append(title, length - 9, length - 4);
            }else {
                stringBuilder.append("part");
            }
            stringBuilder.append("](");
            stringBuilder.append(href);
            stringBuilder.append(")");

            System.out.println(stringBuilder.toString());
        }
        System.out.println();
    }
}
