package edu.gatech.GroceryExpress.services.requests;

public interface CustomerMakerRequest extends Request {
    void setAccount(String account);

    void setFirstName(String firstName);

    void setLastName(String lastName);

    void setPhoneNumber(String phoneNumber);

    void setRating(String rating);

    void setCredit(String credit);

    void setCurrency(String currency);
}
