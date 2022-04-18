package edu.gatech.GroceryExpress.services.requests;

public interface OrderPurchaserRequest extends Request {
    void setStore(String store);

    void setOrder(String order);
}
