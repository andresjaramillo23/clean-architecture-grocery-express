package edu.gatech.GroceryExpress.interactors.responses;

public interface StoreMakerResponse extends SuccessResponse {
    void storeIdentifierAlreadyExistError(String language);

}
