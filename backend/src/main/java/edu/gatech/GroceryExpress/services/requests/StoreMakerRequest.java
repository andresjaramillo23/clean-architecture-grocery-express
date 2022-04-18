package edu.gatech.GroceryExpress.services.requests;

public interface StoreMakerRequest extends Request{
    void setStore(String name);

    void setRevenue(String revenue);

    void setCurrency(String currency);
}
