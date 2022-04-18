package edu.gatech.GroceryExpress.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Order {
    private String store;
    private String order;
    private String drone;
    private String customer;
    private List<Item> items = new ArrayList<>();

    public Order(Properties properties) {
        this.store = properties.getProperty("store");
        this.order = properties.getProperty("order");
        this.drone = properties.getProperty("drone");
        this.customer = properties.getProperty("customer");
    }

    public String getStore() {
        return store;
    }

    public String getOrder() {
        return order;
    }

    public String getDrone() {
        return drone;
    }

    public String getCustomer() {
        return customer;
    }

    public List<Item> getItems() {
        return items;
    }

    public void addItems(Item item) {
        items.add(item);
    }

}
