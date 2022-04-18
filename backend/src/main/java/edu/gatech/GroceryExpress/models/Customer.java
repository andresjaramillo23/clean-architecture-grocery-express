package edu.gatech.GroceryExpress.models;

import java.util.Properties;

public class Customer {
    private String account;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private int rating;
    private int credit;
    private String currency;

    public Customer(Properties properties, String currency) {
        this.account = properties.getProperty("account");
        this.firstName = properties.getProperty("firstName");
        this.lastName = properties.getProperty("lastName");
        this.phoneNumber = properties.getProperty("phoneNumber");
        this.rating = Integer.parseInt(properties.getProperty("rating"));
        this.credit = Integer.parseInt(properties.getProperty("credit"));
        this.currency = currency;
    }

    public String getAccount() {
        return account;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getRating() {
        return rating;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
