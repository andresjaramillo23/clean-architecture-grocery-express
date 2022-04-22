package edu.gatech.GroceryExpress.interactors;


import edu.gatech.GroceryExpress.gateways.GatewayFactory;
import edu.gatech.GroceryExpress.gateways.GatewayFactoryImplementation;
import edu.gatech.GroceryExpress.gateways.GatewayRepository;
import edu.gatech.GroceryExpress.gateways.InMemoryGatewayRepository;
import edu.gatech.GroceryExpress.interactors.responses.StoreMakerResponse;
import edu.gatech.GroceryExpress.models.Store;
import edu.gatech.GroceryExpress.services.requests.StoreMakerRequest;
import edu.gatech.GroceryExpress.utility.GroceryExpressUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

public class StoreMaker implements StoreMakerRequest {
    private StoreMakerResponse response;
    private String store;
    private String revenue;
    private String language;
    private String currency;

    private InMemoryGatewayRepository gatewayFactory;

    public StoreMaker(StoreMakerResponse response, InMemoryGatewayRepository inMemoryGatewayRepository) {
        this.response = Objects.requireNonNull(response);
        this.gatewayFactory = inMemoryGatewayRepository;
    }

    @Override
    public void execute() {
        GatewayRepository gatewayRepository = gatewayFactory;
        List<Store> stores = gatewayRepository.retrieveStores();

        if (stores.isEmpty()) {
            gatewayRepository.registerStore(new Store(store, Integer.parseInt((revenue)), currency));
            response.success(language);
        }
        else if (!GroceryExpressUtility.isMissing(stores, store))
            response.storeIdentifierAlreadyExistError(language);
        else {
            gatewayRepository.registerStore(new Store(store, Integer.parseInt((revenue)), currency));
            response.success(language);
        }
    }

    @Override
    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public void setStore(String name) {
        this.store = name;
    }

    @Override
    public void setRevenue(String revenue) {
        this.revenue = revenue;
    }

    @Override
    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
