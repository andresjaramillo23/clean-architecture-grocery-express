package edu.gatech.GroceryExpress.interactors.responses;

public interface ItemSellerResponse extends SuccessResponse {

    void itemError(String language);

    void storeError(String language);
}
