package edu.gatech.GroceryExpress.services.requests;

public interface OrderCancellerRequest extends Request {
    void setStore(String store);

    void setOrder(String order);
}
