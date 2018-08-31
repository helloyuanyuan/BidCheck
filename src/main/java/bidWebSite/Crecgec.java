package bidWebSite;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import tools.DateTool;

public class Crecgec {

    DateTool dateTool = new DateTool();

    public void search(String searchDate) throws Exception {

        System.out.println("开始搜索 中铁鲁班商务网 " + searchDate + " 招标信息。");

        int total = 0;
        int check = 0;
        int unorder = 0;

        end: for (int i = 1; true; i++) {

            System.out.println("\n开始搜索第 " + i + " 页。");

            String url = "http://www.crecgec.com/forum.php?mod=forumdisplay&fid=2&filter=sortid&sortid=12&page=" + i;
            Document doc = Jsoup.connect(url).get();
            Elements links = doc.body().getElementsByAttributeValue("class", "listContent").select("a[title]");

            for (Element link : links) {
                String linkText = link.text();
                String linkHref = link.attr("abs:href");
                String date = linkText.substring(linkText.length() - 19, linkText.length() - 9);

                String subUrl = linkHref;
                Document subDoc = Jsoup.connect(subUrl).get();
                Elements subLinks = subDoc.body().getElementsByAttributeValue("class", "allNoticCont").select("p[class]");
                String subLinkText = subLinks.text();

                if (date.compareTo(searchDate) > 0) {
                    System.out.println("\n搜索中...");
                    continue;
                } else if (date.compareTo(searchDate) == 0) {
                    total++;
                    System.out.println("\n" + linkText + "\n" + linkHref + "\r");
                    File file = new File("./txt/key.txt");
                    BufferedReader bReader = new BufferedReader(new FileReader(file));
                    String key = null;
                    Boolean finder = false;
                    while ((key = bReader.readLine()) != null) {
                        if (new String(subLinkText.getBytes(), "utf-8").indexOf(new String(key.getBytes(), "utf-8")) != -1) {
                            finder = true;
                            System.out.println("搜索到关键字: " + key + "\r");
                        }
                        continue;
                    }
                    bReader.close();

                    if (finder) {
                        check++;
                        System.out.println("查询到关键字内容，请人工检查！");
                    }
                } else {
                    unorder++;
                    if (unorder == 2) {
                        System.out.println("\n搜索结束，退出。\n");
                        break end;
                    }
                }
            }
        }

        System.out.println("当前日期: " + dateTool.getCurrentDate() + ";\r");
        System.out.println("搜索日期: " + searchDate + ";\r");
        System.out.println("发布总数: " + total + ";\r");
        System.out.println("需详查数: " + check + ";\r");

    }

}
