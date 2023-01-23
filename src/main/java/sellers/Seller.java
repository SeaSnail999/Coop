package sellers;

import common.Category;
import common.Importer;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;

public abstract class Seller {
    public static enum Type {
        retail,
        wholesale
    }
    public static HashMap<Integer, Seller> sellers = new HashMap<>();

    public static void main(String[] args) {
        Okey okey = new Okey(1, "О'КЕЙ", "okeydostavka.ru");
        try {
            okey.parseByLink("http://okeydostavka.ru/msk/molochnye-produkty-syry-iaitso/molochnye-produkty/moloko-i-slivki/bzmzh-moloko-utp-domik-v-derevne-3-2-925ml-tba");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    
    int sellerID;
    String name;
    String site;

    abstract void parseItem(Document document);

    abstract void parseCategory(Category category);

    abstract void parseCatalogue();

    Seller(int sellerID, String name, String site) {
        this.sellerID = sellerID;
        this.name = name;
        this.site = site;
    }

    void parseByLink(String link) throws IOException, InterruptedException {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(link))
                .GET() // GET is default
                .build();

        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());
        Document doc = new Document("");
        System.out.println(response.body());
        doc.text(response.body());
        parseItem(doc);
    }
}
