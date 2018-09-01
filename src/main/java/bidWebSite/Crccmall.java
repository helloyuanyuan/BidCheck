package bidWebSite;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintStream;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import tools.DateUtils;
import tools.SslUtils;

public class Crccmall {

    public void search(String searchDate) throws Exception {

        StringBuilder stringHtml = new StringBuilder();
        PrintStream printStream = new PrintStream(new FileOutputStream("./report/铁建商城网招标信息 " + searchDate + ".html"));

        stringHtml.append("<html><head>");
        stringHtml.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">");
        stringHtml.append("<title>铁建商城网招标信息 " + searchDate + "</title>");
        stringHtml.append("</head>");
        stringHtml.append("<body>");

        int total = 0;
        int check = 0;
        int unorder = 0;

        String message = null;

        message = "铁建商城网 " + searchDate + " 招标信息";
        System.out.println(message);
        stringHtml.append("<div><h2>" + message + "</h2></div>");

        end: for (int i = 1; true; i++) {

            message = "\n第 " + i + " 页。";
            System.out.println(message);
            stringHtml.append("<br><div><b>" + message + "</b></div>");

            String url = "https://www.crccmall.com/cms/infomation/page/list/1/" + i;

            String html = SslUtils.getRequest(url, 30000);
            Document doc = Jsoup.parse(html);

            Elements links = doc.body().getElementsByAttributeValue("class", "col-xs-10").select("tr[onclick]");

            for (Element link : links) {

                String linkText = link.text();
                String linkHref = link.attr("onclick");
                String date = linkText.substring(linkText.length() - 19, linkText.length() - 9);

                linkHref = linkHref.substring(13);
                String subUrl = linkHref.substring(0, linkHref.length() - 3);
                html = SslUtils.getRequest(subUrl, 30000);
                Document subDoc = Jsoup.parse(html);
                Elements subLinks = subDoc.body().getElementsByAttributeValue("class", "form-list").select("p[style]");
                String subLinkText = subLinks.text();

                if (date.compareTo(searchDate) > 0) {

                    message = "\n搜索中...";
                    System.out.println(message);
                    stringHtml.append("<br><div>" + message + "</div>");

                    continue;

                } else if (date.compareTo(searchDate) == 0) {

                    total++;

                    message = "\n" + linkText + "\n" + linkHref + "\r";
                    System.out.println(message);
                    stringHtml.append("<br><div>" + linkText + "</div>");
                    stringHtml.append("<div><a href=\"" + linkHref + "\" target=\"_blank\">" + linkHref + "</a></div>");

                    File file = new File("./txt/key.txt");
                    BufferedReader bReader = new BufferedReader(new FileReader(file));
                    String key = null;
                    Boolean finder = false;

                    while ((key = bReader.readLine()) != null) {

                        if (new String(subLinkText.getBytes(), "utf-8").indexOf(new String(key.getBytes(), "utf-8")) != -1) {

                            finder = true;

                            message = "搜索到关键字: " + key + "\r";
                            System.out.println(message);
                            stringHtml.append("<div style=\"color:#FF0000\"><b>" + message + "</b></div>");

                        }

                        continue;

                    }

                    bReader.close();

                    if (finder) {

                        check++;

                        message = "查询到关键字内容，请人工检查！";
                        System.out.println(message);
                        stringHtml.append("<div style=\"color:#FF0000\"><b>" + message + "</b></div>");

                    }
                } else {

                    unorder++;

                    if (unorder == 2) {

                        message = "\n搜索结束。\n";
                        System.out.println(message);
                        stringHtml.append("<br><div>" + message + "</div><br>");

                        break end;
                    }
                }
            }
        }

        message = "当前日期: " + DateUtils.getCurrentDate() + ";\r";
        System.out.println(message);
        stringHtml.append("<div>" + message + "</div>");

        message = "搜索日期: " + searchDate + ";\r";
        System.out.println(message);
        stringHtml.append("<div>" + message + "</div>");

        message = "发布总数: " + total + ";\r";
        System.out.println(message);
        stringHtml.append("<div><b>" + message + "</b></div>");

        message = "需详查数: " + check + ";\r";
        System.out.println(message);
        stringHtml.append("<div style=\"color:#FF0000\"><b>" + message + "</b></div>");

        stringHtml.append("</body></html>");
        printStream.println(stringHtml.toString());
        printStream.close();

    }
}
