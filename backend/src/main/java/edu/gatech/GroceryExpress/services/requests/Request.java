package edu.gatech.GroceryExpress.services.requests;

public interface Request {
    void execute();

    void setLanguage(String language);
}
