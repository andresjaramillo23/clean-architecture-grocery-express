package edu.gatech.GroceryExpress.services.requests;

public interface OrderDisplayerRequest extends Request {
    void setStore(String store);

    void setCurrency(String currency);
}
