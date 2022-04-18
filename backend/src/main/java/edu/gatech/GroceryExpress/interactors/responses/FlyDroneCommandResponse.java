package edu.gatech.GroceryExpress.interactors.responses;

public interface FlyDroneCommandResponse extends SuccessResponse {

    void storeError(String language);

    void droneError(String language);

    void pilotError(String language);
}

