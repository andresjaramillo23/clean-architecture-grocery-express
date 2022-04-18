package edu.gatech.GroceryExpress.interactors.responses;

public interface ItemRequesterResponse extends SuccessResponse {

    void storeError(String language);

    void orderError(String language);

    void missingItemError(String language);

    void itemDuplicateError(String language);

    void noFundsCustomerError(String language);

    void droneCapacityError(String language);
}
