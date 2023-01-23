package common;

import sellers.Seller;

import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;

public class Importer {
    public static class Category {
        int id;
        String name;
        HashSet<Product> set = new HashSet<>();
    }

    static class Product {
        int id;
        String name;
        URL link;
        float packaging;
        Seller seller;
        Denomination denomination;
        float price;
    }

    static class Denomination {
        int id;
        Category category;
        String name;
        String metric;
    }

    static HashMap<Integer, Category> categories = new HashMap<>();

    static HashMap<Integer, Denomination> denominations = new HashMap<>();

    static HashMap<Integer, Product> products = new HashMap<>();

    static String[] metric = new String[]{};

}
