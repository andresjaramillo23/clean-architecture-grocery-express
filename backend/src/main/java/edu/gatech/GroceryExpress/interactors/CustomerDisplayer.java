package edu.gatech.GroceryExpress.interactors;

import edu.gatech.GroceryExpress.gateways.GatewayFactory;
import edu.gatech.GroceryExpress.gateways.GatewayFactoryImplementation;
import edu.gatech.GroceryExpress.gateways.GatewayRepository;
import edu.gatech.GroceryExpress.interactors.responses.CustomerDisplayerResponse;
import edu.gatech.GroceryExpress.models.Customer;
import edu.gatech.GroceryExpress.services.requests.CustomerDisplayerRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CustomerDisplayer implements CustomerDisplayerRequest {
    private CustomerDisplayerResponse response;
    private String language;
    private String currency;
    private GatewayFactory gatewayFactory;

    public CustomerDisplayer(CustomerDisplayerResponse response, GatewayFactory gatewayFactory) {
        this.response = Objects.requireNonNull(response);
        this.gatewayFactory = gatewayFactory;
    }

    @Override
    public void execute() {
        GatewayRepository gatewayRepository = gatewayFactory.createGatewayRepository();

        List<Customer> customers = new ArrayList<>(gatewayRepository.retrieveCustomers());

        response.displayCustomers(language, currency, customers);
    }

    @Override
    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
