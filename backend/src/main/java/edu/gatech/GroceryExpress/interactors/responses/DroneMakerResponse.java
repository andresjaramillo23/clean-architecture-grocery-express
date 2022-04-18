package edu.gatech.GroceryExpress.interactors.responses;

public interface DroneMakerResponse extends SuccessResponse{

    void droneIdentifierError(String language);

    void storeError(String language);
}
