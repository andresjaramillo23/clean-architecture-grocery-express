package edu.gatech.GroceryExpress.interactors.responses;

public interface OrderPurchaserResponse extends SuccessResponse{

    void storeError(String language);

    void orderError(String language);

    void droneFuelError(String language);

    void droneNeedsPilotError(String language);
}
