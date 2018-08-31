package bidWebSite;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import tools.DateTool;

public class Crccmall {

    DateTool dateTool = new DateTool();

    public void search(String searchDate) throws Exception {

        File writeFile = new File("./txt/Crccmall " + dateTool.getCurrentDate() + " Report.txt");

        if (!writeFile.exists())
            writeFile.createNewFile();

        FileWriter fWriter = new FileWriter(writeFile);
        BufferedWriter bWriter = new BufferedWriter(fWriter);

        int total = 0;
        int check = 0;
        int unorder = 0;

        String message = null;

        message = "开始搜索 铁建商城网 " + searchDate + " 招标信息。";
        System.out.println(message);
        bWriter.write(message);
        bWriter.newLine();

        end: for (int i = 1; true; i++) {

            message = "\n开始搜索第 " + i + " 页。";
            System.out.println(message);
            bWriter.write(message);
            bWriter.newLine();

            String url = "https://www.crccmall.com/cms/infomation/page/list/1/" + i;

            File input = new File("./txt/temp.html");
            Document doc = Jsoup.parse(input, "UTF-8", url);

            Elements links = doc.body().getElementsByAttributeValue("class", "col-xs-10").select("tr[onclick]");

            for (Element link : links) {

                String linkText = link.text();
                String linkHref = link.attr("onclick");
                String date = linkText.substring(linkText.length() - 19, linkText.length() - 9);

                linkHref = linkHref.substring(13);
                String subUrl = linkHref.substring(0, linkHref.length() - 3);
                Document subDoc = Jsoup.connect(subUrl).get();
                Elements subLinks = subDoc.body().getElementsByAttributeValue("class", "form-list").select("p[style]");
                String subLinkText = subLinks.text();

                if (date.compareTo(searchDate) > 0) {

                    message = "\n搜索中...";
                    System.out.println(message);
                    bWriter.write(message);
                    bWriter.newLine();

                    continue;

                } else if (date.compareTo(searchDate) == 0) {

                    total++;

                    message = "\n" + linkText + "\n" + linkHref + "\r";
                    System.out.println(message);
                    bWriter.write(message);
                    bWriter.newLine();

                    File file = new File("./txt/key.txt");
                    BufferedReader bReader = new BufferedReader(new FileReader(file));
                    String key = null;
                    Boolean finder = false;

                    while ((key = bReader.readLine()) != null) {

                        if (new String(subLinkText.getBytes(), "utf-8").indexOf(new String(key.getBytes(), "utf-8")) != -1) {

                            finder = true;

                            message = "搜索到关键字: " + key + "\r";
                            System.out.println(message);
                            bWriter.write(message);
                            bWriter.newLine();

                        }

                        continue;

                    }

                    bReader.close();

                    if (finder) {

                        check++;

                        message = "查询到关键字内容，请人工检查！";
                        System.out.println(message);
                        bWriter.write(message);
                        bWriter.newLine();

                    }
                } else {

                    unorder++;

                    if (unorder == 2) {

                        message = "\n搜索结束，退出。\n";
                        System.out.println(message);
                        bWriter.write(message);
                        bWriter.newLine();

                        break end;
                    }
                }
            }
        }

        message = "当前日期: " + dateTool.getCurrentDate() + ";\r";
        System.out.println(message);
        bWriter.write(message);
        bWriter.newLine();

        message = "搜索日期: " + searchDate + ";\r";
        System.out.println(message);
        bWriter.write(message);
        bWriter.newLine();

        message = "发布总数: " + total + ";\r";
        System.out.println(message);
        bWriter.write(message);
        bWriter.newLine();

        message = "需详查数: " + check + ";\r";
        System.out.println(message);
        bWriter.write(message);
        bWriter.newLine();

        bWriter.close();

    }
}
