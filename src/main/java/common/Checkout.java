package common;

import java.util.HashMap;

public class Checkout {
    int id;
    Customer customer;
    HashMap<Product, Float> goods = new HashMap<>();
    int total;
    enum Status {
        ORDERED,
        PROCESSING,
        DELIVERED
    }


}
