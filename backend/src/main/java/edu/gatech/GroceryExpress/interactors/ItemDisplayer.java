package edu.gatech.GroceryExpress.interactors;

import edu.gatech.GroceryExpress.GroceryExpress;
import edu.gatech.GroceryExpress.gateways.GatewayFactory;
import edu.gatech.GroceryExpress.gateways.GatewayRepository;
import edu.gatech.GroceryExpress.interactors.responses.ItemDisplayerResponse;
import edu.gatech.GroceryExpress.models.Store;
import edu.gatech.GroceryExpress.services.requests.ItemDisplayerRequest;
import edu.gatech.GroceryExpress.utility.GroceryExpressUtility;

import java.util.List;
import java.util.Objects;

public class ItemDisplayer implements ItemDisplayerRequest {
    private ItemDisplayerResponse response;
    private String store;
    private String language;
    private GatewayFactory gatewayFactory;

    public ItemDisplayer(ItemDisplayerResponse response, GatewayFactory gatewayFactory) {
        this.response = Objects.requireNonNull(response);
        this.gatewayFactory = gatewayFactory;
    }

    @Override
    public void execute() {
        GatewayRepository gatewayRepository = gatewayFactory.createGatewayRepository();
        List<Store> stores = gatewayRepository.retrieveStores();

        if (GroceryExpressUtility.isMissing(stores, store))
            response.storeError(language);
        else
            response.displayItems(language, GroceryExpressUtility.getItems(stores, store));
    }

    @Override
    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public void setStore(String store) {
        this.store = store;
    }
}
