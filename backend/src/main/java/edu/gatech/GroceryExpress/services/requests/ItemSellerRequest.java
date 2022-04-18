package edu.gatech.GroceryExpress.services.requests;

public interface ItemSellerRequest extends Request {
    void setStore(String storeName);

    void setItem(String item);

    void setWeight(String price);
}
