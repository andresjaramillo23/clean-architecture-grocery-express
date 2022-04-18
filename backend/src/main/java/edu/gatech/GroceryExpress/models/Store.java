package edu.gatech.GroceryExpress.models;

import java.util.ArrayList;
import java.util.List;

public class Store {
    private String store;
    private int revenue;
    private String currency;
    private List<Drone> drones = new ArrayList<>();
    private List<Item> items = new ArrayList<>();
    private List<Order> orders = new ArrayList<>();

    public Store(String store, int revenue, String currency) {
        this.store = store;
        this.revenue = revenue;
        this.currency = currency;
    }

    public String getStore() {
        return this.store;
    }

    public int getRevenue() {
        return this.revenue;
    }

    public List<Item> getItems() {
        return items;
    }

    public void addItems(Item item) {
        items.add(item);
    }

    public List<Drone> getDrones() {
        return drones;
    }

    public void addDrones(Drone drones) {
        this.drones.add(drones);
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void addOrders(Order order) {
        orders.add(order);
    }

    public void setRevenue(int revenue) {
        this.revenue = revenue;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
