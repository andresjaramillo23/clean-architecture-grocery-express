package edu.gatech.GroceryExpress.interactors;

import edu.gatech.GroceryExpress.GroceryExpress;
import edu.gatech.GroceryExpress.gateways.GatewayFactory;
import edu.gatech.GroceryExpress.gateways.GatewayRepository;
import edu.gatech.GroceryExpress.interactors.responses.PilotMakerResponse;
import edu.gatech.GroceryExpress.models.Pilot;
import edu.gatech.GroceryExpress.services.requests.PilotMakerRequest;

import java.util.List;
import java.util.Objects;
import java.util.Properties;

public class PilotMaker implements PilotMakerRequest {
    private PilotMakerResponse response;
    private String account;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String taxId;
    private String licenseId;
    private String experienceLevel;
    private String language;
    private GatewayFactory gatewayFactory;

    public PilotMaker(PilotMakerResponse response, GatewayFactory gatewayFactory) {
        this.response = Objects.requireNonNull(response);
        this.gatewayFactory = gatewayFactory;
    }

    @Override
    public void execute() {
        GatewayRepository gatewayRepository = gatewayFactory.createGatewayRepository();

        List<Pilot> pilots = gatewayRepository.retrievePilots();

        if (hasDuplicatePilotAccount(pilots))
            response.accountError(language);
        else if (hasDuplicatePilotLicense(pilots))
            response.pilotLicenseError(language);
        else {
            response.success(language);
            gatewayRepository.registerPilot(new Pilot(getProperties()));
        }
    }

    @Override
    public void setLanguage(String language) {
        this.language = language;
    }

    private boolean hasDuplicatePilotAccount(List<Pilot> pilots) {
        return pilots.stream().anyMatch(x -> x.getAccount().equals(account));
    }

    private boolean hasDuplicatePilotLicense(List<Pilot> pilots) {
        return pilots.stream().anyMatch(x -> x.getLicenseId().equals(licenseId));
    }

    @Override
    public void setAccount(String account) {
        this.account = account;
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    @Override
    public void setlicenseId(String licenseId) {
        this.licenseId = licenseId;
    }

    @Override
    public void setExperienceLevel(String experienceLevel) {
        this.experienceLevel = experienceLevel;
    }

    private Properties getProperties() {
        Properties properties = new Properties();
        properties.setProperty("account", account);
        properties.setProperty("firstName", firstName);
        properties.setProperty("lastName", lastName);
        properties.setProperty("phoneNumber", phoneNumber);
        properties.setProperty("taxId", taxId);
        properties.setProperty("licenseId", licenseId);
        properties.setProperty("experienceLevel", experienceLevel);

        return properties;
    }
}
