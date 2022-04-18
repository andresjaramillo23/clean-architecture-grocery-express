package edu.gatech.GroceryExpress.interactors.responses;


import edu.gatech.GroceryExpress.models.Item;

import java.util.List;

public interface ItemDisplayerResponse {
    void displayItems(String language, List<Item> items);

    void storeError(String language);

}
