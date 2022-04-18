package edu.gatech.GroceryExpress.services.requests;

public interface ItemRequesterRequest extends Request {
    void setStore(String store);

    void setOrder(String order);

    void setItem(String item);

    void setQuantity(String quantity);

    void setUnitPrice(String unitPrice);

    void setCurrency(String currency);
}
