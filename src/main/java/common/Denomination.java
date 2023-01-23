package common;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class Denomination {
    static enum Metric {
        gram,
        liter,
        piece,
        kg
    }

    int id;
    Category category;
    String name;
    Metric metric;

    static HashMap<Integer, Denomination> denominations = new HashMap<>();

    static String cmd = "SELECT * FROM `product_denominations`";

    static {
        ResultSet rs = MySQL.select(cmd);
        try {
            while (rs.next()) {
                int id = rs.getInt("id");
                Category category = null;
                String name = rs.getString("name");
                Metric metric = Metric.valueOf(rs.getString("metric"));

                Denomination denomination = new Denomination(id, category, name, metric);

                denominations.put(id, denomination);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Denomination(int id, Category category, String name, Metric metric) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.metric = metric;
    }
}
