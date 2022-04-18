package edu.gatech.GroceryExpress.interactors;

import edu.gatech.GroceryExpress.GroceryExpress;
import edu.gatech.GroceryExpress.gateways.GatewayFactory;
import edu.gatech.GroceryExpress.gateways.GatewayRepository;
import edu.gatech.GroceryExpress.interactors.responses.CustomerMakerResponse;
import edu.gatech.GroceryExpress.models.Customer;
import edu.gatech.GroceryExpress.services.requests.CustomerMakerRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

public class CustomerMaker implements CustomerMakerRequest {
    private CustomerMakerResponse response;
    private String account;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String rating;
    private String credit;
    private String language;
    private String currency;
    private GatewayFactory gatewayFactory;

    public CustomerMaker(CustomerMakerResponse response, GatewayFactory gatewayFactory) {
        this.response = Objects.requireNonNull(response);
        this.gatewayFactory = gatewayFactory;
    }

    @Override
    public void execute() {
        GatewayRepository gatewayRepository = gatewayFactory.createGatewayRepository();

        List<Customer> customers = new ArrayList<>(gatewayRepository.retrieveCustomers());

        if (hasDuplicatedCustomer(customers))
            response.customerAccountError(language);
        else {
            gatewayRepository.registerCustomer(new Customer(getProperties(), currency));
            response.success(language);
        }
    }

    @Override
    public void setLanguage(String language) {
        this.language = language;
    }

    private boolean hasDuplicatedCustomer(List<Customer> customers) {
        return customers.stream().anyMatch(x -> x.getAccount().equals(account));
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
    public void setRating(String rating) {
        this.rating = rating;
    }

    @Override
    public void setCredit(String credit) {
        this.credit = credit;
    }

    @Override
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    private Properties getProperties() {
        Properties properties = new Properties();
        properties.setProperty("account", account);
        properties.setProperty("firstName", firstName);
        properties.setProperty("lastName", lastName);
        properties.setProperty("phoneNumber", phoneNumber);
        properties.setProperty("rating", rating);
        properties.setProperty("credit", credit);

        return properties;
    }
}
