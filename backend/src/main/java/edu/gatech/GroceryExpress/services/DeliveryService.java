package edu.gatech.GroceryExpress.services;

import edu.gatech.GroceryExpress.interactors.UseCaseCreator;
import edu.gatech.GroceryExpress.interactors.responses.*;
import edu.gatech.GroceryExpress.services.requests.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class DeliveryService {

    @Autowired
    private UseCaseCreator requestFactory;

    public DeliveryService() {
    }

    public void makeStoreRequest(String language, String currency, Properties properties, StoreMakerResponse response) {
        StoreMakerRequest request = requestFactory.makeStore(response);
        request.setStore(properties.getProperty("store"));
        request.setRevenue(properties.getProperty("revenue"));
        request.setLanguage(language);
        request.setCurrency(currency);
        request.execute();
    }

    public void displayStoresRequest(String language, String currency, StoreDisplayerResponse response) {
        StoreDisplayerRequest request = requestFactory.displayStores(response);
        request.setLanguage(language);
        request.setCurrency(currency);
        request.execute();
    }

    public void sellItemRequest(String language, Properties properties, ItemSellerResponse response) {
        ItemSellerRequest request = requestFactory.sellItem(response);
        request.setStore(properties.getProperty("store"));
        request.setItem(properties.getProperty("item"));
        request.setWeight(properties.getProperty("weight"));
        request.setLanguage(language);
        request.execute();
    }

    public void displayItemRequest(String language, String store, ItemDisplayerResponse response) {
        ItemDisplayerRequest request = requestFactory.displayItem(response);
        request.setStore(store);
        request.setLanguage(language);
        request.execute();
    }

    public void makePilotRequest(String language, Properties properties, PilotMakerResponse response) {
        PilotMakerRequest request = requestFactory.makePilot(response);
        request.setAccount(properties.getProperty("account"));
        request.setFirstName(properties.getProperty("firstName"));
        request.setLastName(properties.getProperty("lastName"));
        request.setPhoneNumber(properties.getProperty("phoneNumber"));
        request.setTaxId(properties.getProperty("taxId"));
        request.setlicenseId(properties.getProperty("licenseId"));
        request.setExperienceLevel(properties.getProperty("experienceLevel"));
        request.setLanguage(language);
        request.execute();
    }

    public void displayPilotRequest(String language, PilotDisplayerResponse response) {
        PilotDisplayerRequest request = requestFactory.displaypilots(response);
        request.setLanguage(language);
        request.execute();
    }

    public void makeDroneRequest(String language, Properties properties, DroneMakerResponse response) {
        DroneMakerRequest request = requestFactory.makeDrone(response);
        request.setStore(properties.getProperty("store"));
        request.setDrone(properties.getProperty("drone"));
        request.setCapacity(properties.getProperty("capacity"));
        request.setFuel(properties.getProperty("fuel"));
        request.setLanguage(language);
        request.execute();
    }

    public void displayDronesRequest(String language, String store, DroneDisplayerResponse response) {
        DroneDisplayerRequest request = requestFactory.displayDrones(response);
        request.setStore(store);
        request.setLanguage(language);
        request.execute();
    }

    public void flyDroneRequest(String language, Properties properties, FlyDroneCommandResponse response) {
        FlyDroneCommandRequest request = requestFactory.flyDrone(response);
        request.setStore(properties.getProperty("store"));
        request.setDrone(properties.getProperty("drone"));
        request.setPilotAccount(properties.getProperty("pilotAccount"));
        request.setLanguage(language);
        request.execute();
    }

    public void makeCustomerRequest(String language, String currency, Properties properties, CustomerMakerResponse response) {
        CustomerMakerRequest request = requestFactory.makeCustomer(response);
        request.setAccount(properties.getProperty("account"));
        request.setFirstName(properties.getProperty("firstName"));
        request.setLastName(properties.getProperty("lastName"));
        request.setPhoneNumber(properties.getProperty("phoneNumber"));
        request.setRating(properties.getProperty("rating"));
        request.setCredit(properties.getProperty("credit"));
        request.setLanguage(language);
        request.setCurrency(currency);
        request.execute();
    }

    public void displayCustomerRequest(String language, String currency, CustomerDisplayerResponse response) {
        CustomerDisplayerRequest request = requestFactory.displayCustomer(response);
        request.setLanguage(language);
        request.setCurrency(currency);
        request.execute();
    }

    public void startOrderRequest(String language, Properties properties, OrderStarterResponse response) {
        OrderStarterRequest request = requestFactory.startOrder(response);
        request.setStore(properties.getProperty("store"));
        request.setOrder(properties.getProperty("order"));
        request.setDrone(properties.getProperty("drone"));
        request.setCustomer(properties.getProperty("customer"));
        request.setLanguage(language);
        request.execute();
    }

    public void displayOrdersRequest(String language, String currency, String store, OrderDisplayerResponse response) {
        OrderDisplayerRequest request = requestFactory.displayOrders(response);
        request.setStore(store);
        request.setLanguage(language);
        request.setCurrency(currency);
        request.execute();
    }

    public void requestItemRequest(String language, String currency, Properties properties, ItemRequesterResponse response) {
        ItemRequesterRequest request = requestFactory.requestItem(response);
        request.setStore(properties.getProperty("store"));
        request.setOrder(properties.getProperty("order"));
        request.setItem(properties.getProperty("item"));
        request.setQuantity(properties.getProperty("quantity"));
        request.setUnitPrice(properties.getProperty("unitPrice"));
        request.setLanguage(language);
        request.setCurrency(currency);
        request.execute();
    }

    public void purchaseOrderRequest(String language, String store, String order, OrderPurchaserResponse response) {
        OrderPurchaserRequest request = requestFactory.purchaseOrder(response);
        request.setStore(store);
        request.setOrder(order);
        request.setLanguage(language);
        request.execute();
    }

    public void cancelOrderRequest(String language, String store, String order, OrderCancellerResponse response) {
        OrderCancellerRequest request = requestFactory.cancelOrder(response);
        request.setStore(store);
        request.setOrder(order);
        request.setLanguage(language);
        request.execute();
    }
}
