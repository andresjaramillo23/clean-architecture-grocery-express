package edu.gatech.GroceryExpress.interactors;


import edu.gatech.GroceryExpress.GroceryExpress;
import edu.gatech.GroceryExpress.gateways.GatewayFactory;
import edu.gatech.GroceryExpress.gateways.GatewayRepository;
import edu.gatech.GroceryExpress.gateways.InMemoryGatewayRepository;
import edu.gatech.GroceryExpress.interactors.responses.DroneMakerResponse;
import edu.gatech.GroceryExpress.models.Drone;
import edu.gatech.GroceryExpress.models.Store;
import edu.gatech.GroceryExpress.services.requests.DroneMakerRequest;
import edu.gatech.GroceryExpress.utility.GroceryExpressUtility;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

public class DroneMaker implements DroneMakerRequest {
    private DroneMakerResponse response;
    private String store;
    private String drone;
    private String capacity;
    private String deliveries;
    private String language;
    private InMemoryGatewayRepository gatewayFactory;

    public DroneMaker(DroneMakerResponse response, InMemoryGatewayRepository gatewayFactory) {
        this.response = Objects.requireNonNull(response);
        this.gatewayFactory = gatewayFactory;
    }

    @Override
    public void execute() {
        GatewayRepository gatewayRepository = gatewayFactory;
        List<Store> stores = new ArrayList<>(gatewayRepository.retrieveStores());

        if (GroceryExpressUtility.isMissing(stores, store))
            response.storeError(language);
        else if (hasDuplicateDroneIdentifier(stores))
            response.droneIdentifierError(language);
        else {
            response.success(language);
            addDroneToStore(stores);
            gatewayRepository.registerStore(GroceryExpressUtility.getStore(stores, store));
        }
    }

    @Override
    public void setLanguage(String language) {
        this.language = language;
    }

    private void addDroneToStore(List<Store> stores) {
        Store store = GroceryExpressUtility.getStore(stores, this.store);
        if (store != null)
            store.addDrones(new Drone(getProperties()));
    }

    private boolean hasDuplicateDroneIdentifier(List<Store> stores) {
        List<Drone> drones = GroceryExpressUtility.getDrones(stores, this.store);
        return drones.stream().anyMatch(x -> x.getDrone().equals(drone));
    }

    @Override
    public void setStore(String store) {
        this.store = store;
    }

    @Override
    public void setDrone(String droneIdentifier) {
        this.drone = droneIdentifier;
    }

    @Override
    public void setCapacity(String weightCapacity) {
        this.capacity = weightCapacity;
    }

    @Override
    public void setFuel(String numberOfDeliveries) {
        this.deliveries = numberOfDeliveries;
    }


    private Properties getProperties() {
        Properties properties = new Properties();
        properties.setProperty("store", store);
        properties.setProperty("drone", drone);
        properties.setProperty("capacity", capacity);
        properties.setProperty("fuel", deliveries);

        return properties;
    }
}
