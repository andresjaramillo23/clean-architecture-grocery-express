package edu.gatech.GroceryExpress.interactors.responses;


import edu.gatech.GroceryExpress.models.Store;

import java.util.List;

public interface StoreDisplayerResponse {
    void displayStores(String language, String currency, List<Store> stores);
}
