package edu.gatech.GroceryExpress.interactors.responses;

public interface OrderStarterResponse extends SuccessResponse {

    void customerAccountError(String language);

    void droneIdentifierError(String language);

    void orderError(String language);

    void storeError(String language);
}
