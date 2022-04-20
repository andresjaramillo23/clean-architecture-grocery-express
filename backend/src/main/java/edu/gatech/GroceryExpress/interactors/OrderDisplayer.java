package edu.gatech.GroceryExpress.interactors;

import edu.gatech.GroceryExpress.GroceryExpress;
import edu.gatech.GroceryExpress.gateways.GatewayFactory;
import edu.gatech.GroceryExpress.gateways.GatewayRepository;
import edu.gatech.GroceryExpress.gateways.InMemoryGatewayRepository;
import edu.gatech.GroceryExpress.interactors.responses.OrderDisplayerResponse;
import edu.gatech.GroceryExpress.models.Store;
import edu.gatech.GroceryExpress.services.requests.OrderDisplayerRequest;
import edu.gatech.GroceryExpress.utility.GroceryExpressUtility;

import java.util.List;
import java.util.Objects;

public class OrderDisplayer implements OrderDisplayerRequest {
    private OrderDisplayerResponse response;
    private String store;
    private String language;
    private String currency;
    private InMemoryGatewayRepository gatewayFactory;

    public OrderDisplayer(OrderDisplayerResponse response, InMemoryGatewayRepository gatewayFactory) {
        this.response = Objects.requireNonNull(response);
        this.gatewayFactory = gatewayFactory;
    }

    @Override
    public void execute() {
        GatewayRepository gatewayRepository = gatewayFactory;
        List<Store> stores = gatewayRepository.retrieveStores();

        if (GroceryExpressUtility.isMissing(stores, this.store))
            response.storeError(language);
        else
            response.displayOrders(language, currency, GroceryExpressUtility.getOrders(stores, this.store));
    }

    @Override
    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public void setStore(String store) {
        this.store = store;
    }

    @Override
    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
