package edu.gatech.GroceryExpress.services.requests;

public interface OrderStarterRequest extends Request {
    void setStore(String store);

    void setOrder(String order);

    void setDrone(String drone);

    void setCustomer(String customer);
}
