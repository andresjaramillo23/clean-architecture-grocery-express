package edu.gatech.GroceryExpress.interactors.responses;

public interface OrderCancellerResponse extends SuccessResponse{

    void storeError(String language);

    void orderError(String language);
}
