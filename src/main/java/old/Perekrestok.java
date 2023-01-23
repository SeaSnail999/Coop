package old;

import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.*;
import sellers.Seller;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;

public class Perekrestok {
    static FileWriter writer;

    public static void main(String[] args) throws IOException {
        //handlePage("https://www.perekrestok.ru/cat/mc/174/ryba");

        HashSet<String> links = getLinks();
        writer = new FileWriter("/home/user/Documents/result.txt");
        for (String link : links) {
            handlePage(link);
        }
        writer.close();
    }

    static HashSet<String> getLinks() throws IOException {
        String a = "https://www.perekrestok.ru/cat";

        Document doc = Jsoup.connect(a).get();
        Elements listNews = doc.getElementsByClass("catalog__filter");
        Elements links = listNews.get(0).getElementsByAttribute("href");
        HashSet<String> result = new HashSet<>();
        for (Element link : links) {
            result.add("https://perekrestok.ru" + link.attr("href"));
        }
        return result;
        //System.out.println(links);
    }

    static void handlePage(String a) throws IOException {
        Document doc = Jsoup.connect(a).get();
        Elements listNews = doc.getElementsByClass("product-card__content");
        for (Element element : listNews) {
            handleElement(element);
        }
    }

    static void handleElement(Element element) throws IOException {
        String name = element.getElementsByClass("product-card__title").toString();
        int begin = name.indexOf('>');
        int end = name.indexOf("</div>");
        String price = element.getElementsByClass("price-new").toString();
        int begin1 = price.indexOf('>');
        int end1 = price.indexOf("</div>");

        String nameParsed = name.substring(begin + 2, end - 1);
        String priceParsed = price.substring(begin1 + 2, end1 - 11).replace(" ", "");
        writer.write(nameParsed + '\t' + priceParsed + '\n');
    }
}
