package sellers;

import common.Category;
import common.Importer;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Okey extends Seller {
    Okey(int sellerID, String name, String site) {
        super(sellerID, name, site);
    }

    @Override
    void parseItem(Document document) {
        Element nameElement = document.getElementById("PageHeading_3074457345618267456");
        System.out.println(nameElement.text());
        Element price = document.getElementById("offerPrice_184831");
        System.out.println(price);
    }

    @Override
    void parseCategory(Category category) {

    }

    @Override
    void parseCatalogue() {

    }
}
