package edu.gatech.GroceryExpress.models;

import java.util.Properties;

public class Pilot {
    private String account;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String taxId;
    private String licenseId;
    private int experienceLevel;

    public Pilot(Properties properties) {
        this.account = properties.getProperty("account");
        this.firstName = properties.getProperty("firstName");
        this.lastName = properties.getProperty("lastName");
        this.phoneNumber = properties.getProperty("phoneNumber");
        this.taxId = properties.getProperty("taxId");
        this.licenseId = properties.getProperty("licenseId");
        this.experienceLevel = Integer.parseInt(properties.getProperty("experienceLevel"));
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

    public String getTaxId() {
        return taxId;
    }

    public String getLicenseId() {
        return licenseId;
    }

    public int getExperienceLevel() {
        return experienceLevel;
    }

    public void setExperienceLevel(int experienceLevel) {
        this.experienceLevel = experienceLevel;
    }
}
