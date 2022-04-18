package edu.gatech.GroceryExpress.models;

public class Item {
    private String name;
    private int weight;
    private int unitPrice = 0;
    private int quantity = 0;
    private String currency;

    public Item(String name, int weight, String currency) {
        this.name = name;
        this.weight = weight;
        this.currency = currency;
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
