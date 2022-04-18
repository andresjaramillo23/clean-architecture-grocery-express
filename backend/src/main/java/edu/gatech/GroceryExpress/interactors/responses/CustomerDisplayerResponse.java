package edu.gatech.GroceryExpress.interactors.responses;


import edu.gatech.GroceryExpress.models.Customer;

import java.util.List;

public interface CustomerDisplayerResponse {
    void displayCustomers(String language, String currency, List<Customer> customers);
}
