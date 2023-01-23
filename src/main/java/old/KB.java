package old;

import common.Resources;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileWriter;
import java.io.IOException;

public class KB {
    static FileWriter writer;

    public static void main(String[] args) throws IOException {
        initDriver();
    }

    static void initDriver() {
        WebDriver driver = new FirefoxDriver();

        driver.get("https://krasnoeibeloe.ru/catalog/soki-i-nektary/?PAGEN_1=2");

        //driver.quit();
    }

    void parseCatalogue() {

        String pages = Resources.readRecourse("KB");
        String[] parts = pages.split("\n");

        try {
            writer = new FileWriter("/home/user/Documents/result.txt");
            //for (String page : parts)
            //  handlePage(page);
            handlePage("https://krasnoeibeloe.ru/catalog/soki-i-nektary/?PAGEN_1=2");
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void handlePage(String a) throws IOException {
        Document doc = Jsoup.connect(a).get();
        System.out.println(doc);
        Elements listNews = doc.getElementsByClass("catalog_product_item_cont");
        for (Element element : listNews) {
            handleElement(element);
            System.out.println(element);
        }
    }

    static void handleElement(Element element) throws IOException {
        String name = element.getElementsByClass("product_item_name").toString();
        System.out.println(name);
        int begin = name.indexOf('>');
        int end = name.indexOf("</div>");
        String price = element.getElementsByClass("i_price").toString();
        System.out.println(price);
        int begin1 = price.indexOf('>');
        int end1 = price.indexOf("</div>");

        String nameParsed = name.substring(begin + 2, end - 1);
        String priceParsed = price.substring(begin1 + 2, end1 - 11).replace(" ", "");
        //writer.write(nameParsed + '\t' + priceParsed + '\n');
    }
}
