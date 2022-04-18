package edu.gatech.GroceryExpress.interactors.responses;

public interface PilotMakerResponse extends SuccessResponse {

    void accountError(String language);

    void pilotLicenseError(String language);
}
