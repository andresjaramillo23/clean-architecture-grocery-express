package edu.gatech.GroceryExpress.interactors.responses;


import edu.gatech.GroceryExpress.models.Order;

import java.util.List;

public interface OrderDisplayerResponse {
    void displayOrders(String language,String currency, List<Order> orders);

    void storeError(String language);
}
