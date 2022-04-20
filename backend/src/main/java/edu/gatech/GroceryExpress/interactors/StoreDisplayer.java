package edu.gatech.GroceryExpress.interactors;

import edu.gatech.GroceryExpress.GroceryExpress;
import edu.gatech.GroceryExpress.gateways.GatewayFactory;
import edu.gatech.GroceryExpress.gateways.GatewayRepository;
import edu.gatech.GroceryExpress.gateways.InMemoryGatewayRepository;
import edu.gatech.GroceryExpress.interactors.responses.StoreDisplayerResponse;
import edu.gatech.GroceryExpress.models.Store;
import edu.gatech.GroceryExpress.services.requests.StoreDisplayerRequest;

import java.util.List;
import java.util.Objects;

public class StoreDisplayer implements StoreDisplayerRequest {
    private StoreDisplayerResponse response;
    private String language;
    private String currency;
    private InMemoryGatewayRepository gatewayFactory;

    public StoreDisplayer(StoreDisplayerResponse response, InMemoryGatewayRepository gatewayFactory) {
        this.response = Objects.requireNonNull(response);
        this.gatewayFactory = gatewayFactory;
    }

    @Override
    public void execute() {
        GatewayRepository gatewayRepository = gatewayFactory;
        List<Store> stores = gatewayRepository.retrieveStores();

        response.displayStores(language, currency, stores);
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
