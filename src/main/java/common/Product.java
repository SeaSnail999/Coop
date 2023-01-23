package common;

import sellers.Seller;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Product {
    int id;
    String name;
    String link;
    float packaging;
    Seller seller;
    Denomination denomination;
    float price;
    float unitPrice;

    static String commandTemplate = "SELECT * FROM `products` WHERE `denomination` = %d AND `type` = '%s' ORDER BY `unit_price` ASC";

    static float getCoopPrice(int denomination) {
        float cheapestRetailPrice = getCheapest(denomination, Seller.Type.retail).unitPrice;
        float cheapestWholesalePrice = getCheapest(denomination, Seller.Type.wholesale).unitPrice;
        if (cheapestWholesalePrice >= cheapestRetailPrice) return 0;
        float marginality = (cheapestRetailPrice - cheapestWholesalePrice)/cheapestWholesalePrice;

        double result;
        if (marginality < 0.1) {
            result = cheapestWholesalePrice * 1.04;
        } else {
            double doubled = cheapestRetailPrice + 0.9 * cheapestWholesalePrice;
            result = doubled / 2 * 1.04;
        }
        return (float) result;
    }

    static Product getCheapest(int denomination, Seller.Type type) {
        String cmd = commandTemplate.formatted(denomination, type);
        ResultSet res = MySQL.select(cmd);
        try {
            if (!res.next()) {
                System.out.println("nomenclature not found");
                return null;
            }
            int id = res.getInt("id");
            String name = res.getString("name");
            String link = res.getString("link");
            float packaging = res.getFloat("packaging");
            float price = res.getFloat("price");
            float unitPrice = res.getFloat("unit_price");
            return new Product(id, name, link, packaging, price, unitPrice);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Product(int id, String name, String link, float packaging, float price, float unitPrice) {
        this.id = id;
        this.name = name;
        this.link = link;
        this.packaging = packaging;
        this.price = price;
        this.unitPrice = unitPrice;

        int rem = id/100;
        this.denomination = Denomination.denominations.get(rem%100);

        rem = id/100;
        this.seller = Seller.sellers.get(rem);
    }
}
