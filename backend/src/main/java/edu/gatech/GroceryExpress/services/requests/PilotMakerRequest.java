package edu.gatech.GroceryExpress.services.requests;

public interface PilotMakerRequest extends Request {
    void setAccount(String pilotAccount);

    void setFirstName(String firstName);

    void setLastName(String lastName);

    void setPhoneNumber(String phoneNumber);

    void setTaxId(String taxId);

    void setlicenseId(String licenseId);

    void setExperienceLevel(String experienceLevel);
}
