package edu.gatech.GroceryExpress.services;

import edu.gatech.GroceryExpress.interactors.responses.*;
import edu.gatech.GroceryExpress.presenters.*;
import edu.gatech.GroceryExpress.presenters.models.DeliveryServiceViewModel;
import edu.gatech.GroceryExpress.presenters.views.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;


@Service
public class ProcessFileService implements StoreMakerView, StoreDisplayerView, ItemSellerView, ItemDisplayerView, PilotMakerView, PilotDisplayerView, DroneMakerView, DroneDisplayerView, FlyDroneCommandView, CustomerMakerView, CustomerDisplayerView, OrderStarterView, OrderDisplayerView, ItemRequesterView, OrderCancellerView, OrderPurchaserView {

    private DeliveryService deliveryService;
    private final DeliveryServiceViewModel model = new DeliveryServiceViewModel();

    final String WORD_DELIMITER = ",";
    final String NEW_LINE_DELIMITER = "\\n+";

    public ProcessFileService(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    public String processFile(MultipartFile file) {
        String responseBody = "";
        StringBuilder bodyBuilder = new StringBuilder();
        try {
            List<String> lines = extractLines(file);
            for (String line : lines) {
                if (line.contains("//")) {
                    System.out.println("> " + line);
                    bodyBuilder.append(String.format("> %s\n", line));
                } else {
                    String[] input = line.split(WORD_DELIMITER);
                    System.out.println("> " + line);
                    bodyBuilder.append(String.format("> %s\n", line));

                    switch (input[0]) {
                        case "make_store":
                            StoreMakerResponse storeMakerResponse = new StoreMakerPresenter(this);
                            Properties properties = new Properties();
                            properties.setProperty("store", input[1]);
                            properties.setProperty("revenue", input[2]);
                            deliveryService.makeStoreRequest("", "", properties, storeMakerResponse);
                            System.out.println(this.model.makeStoreStatus);
                            bodyBuilder.append(this.model.makeStoreStatus);
                            break;
                        case "display_stores":
                            StoreDisplayerPresenter storeDisplayerPresenter = new StoreDisplayerPresenter(this);
                            deliveryService.displayStoresRequest("", "", storeDisplayerPresenter);
                            System.out.println(this.model.displayStores);
                            bodyBuilder.append(this.model.displayStores);
                            break;
                        case "sell_item":
                            ItemSellerResponse itemSellerResponse = new ItemSellerPresenter(this);
                            properties = new Properties();
                            properties.setProperty("store", input[1]);
                            properties.setProperty("item", input[2]);
                            properties.setProperty("weight", input[3]);
                            deliveryService.sellItemRequest("", properties, itemSellerResponse);
                            System.out.println(this.model.sellItemStatus);
                            bodyBuilder.append(this.model.sellItemStatus);
                            break;
                        case "display_items":
                            ItemDisplayerResponse itemDisplayerResponse = new ItemDisplayerPresenter(this);
                            deliveryService.displayItemRequest("", input[1], itemDisplayerResponse);
                            System.out.println(this.model.displayItems);
                            bodyBuilder.append(this.model.displayItems);
                            break;
                        case "make_pilot":
                            properties = new Properties();
                            properties.setProperty("account", input[1]);
                            properties.setProperty("firstName", input[2]);
                            properties.setProperty("lastName", input[3]);
                            properties.setProperty("phoneNumber", input[4]);
                            properties.setProperty("taxId", input[5]);
                            properties.setProperty("licenseId", input[6]);
                            properties.setProperty("experienceLevel", input[7]);

                            PilotMakerResponse pilotMakerResponse = new PilotMakerPresenter(this);
                            deliveryService.makePilotRequest("", properties, pilotMakerResponse);
                            System.out.println(this.model.makePilotStatus);
                            bodyBuilder.append(this.model.makePilotStatus);
                            break;
                        case "display_pilots":
                            PilotDisplayerResponse pilotDisplayerResponse = new PilotDisplayerPresenter(this);
                            deliveryService.displayPilotRequest("", pilotDisplayerResponse);
                            System.out.println(this.model.displayPilots);
                            bodyBuilder.append(this.model.displayPilots);
                            break;
                        case "make_drone":
                            properties = new Properties();
                            properties.setProperty("store", input[1]);
                            properties.setProperty("drone", input[2]);
                            properties.setProperty("capacity", input[3]);
                            properties.setProperty("fuel", input[4]);

                            DroneMakerResponse droneMakerResponse = new DroneMakerPresenter(this);
                            deliveryService.makeDroneRequest("", properties, droneMakerResponse);
                            System.out.println(this.model.makeDroneStatus);
                            bodyBuilder.append(this.model.makeDroneStatus);
                            break;
                        case "display_drones":
                            DroneDisplayerResponse droneDisplayerResponse = new DroneDisplayerPresenter(this);
                            deliveryService.displayDronesRequest("", input[1], droneDisplayerResponse);
                            System.out.println(this.model.displayDrones);
                            bodyBuilder.append(this.model.displayDrones);
                            break;
                        case "fly_drone":
                            properties = new Properties();
                            properties.setProperty("store", input[1]);
                            properties.setProperty("drone", input[2]);
                            properties.setProperty("pilotAccount", input[3]);

                            FlyDroneCommandResponse flyDroneCommandResponse = new FlyDroneCommandPresenter(this);
                            deliveryService.flyDroneRequest("", properties, flyDroneCommandResponse);
                            System.out.println(this.model.flyDroneStatus);
                            bodyBuilder.append(this.model.flyDroneStatus);
                            break;
                        case "make_customer":
                            properties = new Properties();
                            properties.setProperty("account", input[1]);
                            properties.setProperty("firstName", input[2]);
                            properties.setProperty("lastName", input[3]);
                            properties.setProperty("phoneNumber", input[4]);
                            properties.setProperty("rating", input[5]);
                            properties.setProperty("credit", input[6]);

                            CustomerMakerResponse customerMakerResponse = new CustomerMakerPresenter(this);
                            deliveryService.makeCustomerRequest("", "", properties, customerMakerResponse);
                            System.out.println(this.model.makeCustomerStatus);
                            bodyBuilder.append(this.model.makeCustomerStatus);
                            break;
                        case "display_customers":
                            CustomerDisplayerResponse customerDisplayerResponse = new CustomerDisplayerPresenter(this);
                            deliveryService.displayCustomerRequest("", "", customerDisplayerResponse);
                            System.out.println(this.model.displayCustomers);
                            bodyBuilder.append(this.model.displayCustomers);
                            break;
                        case "start_order":
                            properties = new Properties();
                            properties.setProperty("store", input[1]);
                            properties.setProperty("order", input[2]);
                            properties.setProperty("drone", input[3]);
                            properties.setProperty("customer", input[4]);

                            OrderStarterResponse orderStarterResponse = new OrderStarterPresenter(this);
                            deliveryService.startOrderRequest("", properties, orderStarterResponse);
                            System.out.println(this.model.startOrderStatus);
                            bodyBuilder.append(this.model.startOrderStatus);
                            break;
                        case "display_orders":
                            OrderDisplayerResponse orderDisplayerResponse = new OrderDisplayerPresenter(this);
                            deliveryService.displayOrdersRequest("", "", input[1], orderDisplayerResponse);
                            System.out.println(this.model.displayOrders);
                            bodyBuilder.append(this.model.displayOrders);
                            break;
                        case "request_item":
                            properties = new Properties();
                            properties.setProperty("store", input[1]);
                            properties.setProperty("order", input[2]);
                            properties.setProperty("item", input[3]);
                            properties.setProperty("quantity", input[4]);
                            properties.setProperty("unitPrice", input[5]);

                            ItemRequesterResponse itemRequesterResponse = new ItemRequesterPresenter(this);
                            deliveryService.requestItemRequest("", "", properties, itemRequesterResponse);
                            System.out.println(this.model.requestItemStatus);
                            bodyBuilder.append(this.model.requestItemStatus);
                            break;
                        case "purchase_order":
                            OrderPurchaserResponse orderPurchaserResponse = new OrderPurchaserPresenter(this);
                            deliveryService.purchaseOrderRequest("", input[1], input[2], orderPurchaserResponse);
                            System.out.println(this.model.purchaseOrderStatus);
                            bodyBuilder.append(this.model.purchaseOrderStatus);
                            break;
                        case "cancel_order":
                            OrderCancellerResponse orderCancellerResponse = new OrderCancellerPresenter(this);
                            deliveryService.cancelOrderRequest("", input[1], input[2], orderCancellerResponse);
                            System.out.println(this.model.cancelOrderStatus);
                            bodyBuilder.append(this.model.cancelOrderStatus);
                            break;
                        case "stop":
                            System.out.println("stop acknowledged");
                            responseBody = bodyBuilder.toString();
                            break;
                    }
                }
            }

        } catch (Exception e) {
            System.out.println("Could not read file. Cause: " + e.getMessage());
        }
        return responseBody;
    }

    private List<String> extractLines(MultipartFile file) throws IOException {
        String content = new String(file.getBytes());
        return Arrays.asList(content.split(NEW_LINE_DELIMITER));
    }

    @Override
    public void makeStoreStatus(String status) {
        this.model.makeStoreStatus = status;
    }

    @Override
    public void displayStores(DeliveryServiceViewModel model) {
        this.model.displayStores = model.displayStores;
        this.model.stores = model.stores;
    }

    @Override
    public void sellItemStatus(String status) {
        this.model.sellItemStatus = status;
    }

    @Override
    public void displayItems(String items) {
        this.model.displayItems = items;
    }

    @Override
    public void makePilotStatus(String status) {
        this.model.makePilotStatus = status;
    }

    @Override
    public void displayPilots(DeliveryServiceViewModel model) {
        this.model.displayPilots = model.displayPilots;
        this.model.pilots = model.pilots;
    }

    @Override
    public void makeDroneStatus(String status) {
        this.model.makeDroneStatus = status;
    }

    @Override
    public void displayDrones(String drones) {
        this.model.displayDrones = drones;
    }

    @Override
    public void flyDroneStatus(String model) {
        this.model.flyDroneStatus = model;
    }

    @Override
    public void makeCustomerStatus(String status) {
        this.model.makeCustomerStatus = status;
    }

    @Override
    public void displayCustomers(DeliveryServiceViewModel model) {
        this.model.displayCustomers = model.displayCustomers;
        this.model.customers = model.customers;
    }

    @Override
    public void startOrderStatus(String status) {
        this.model.startOrderStatus = status;
    }

    @Override
    public void displayOrders(String orders) {
        this.model.displayOrders = orders;
    }

    @Override
    public void requestItemStatus(String status) {
        this.model.requestItemStatus = status;
    }

    @Override
    public void orderPurchaserStatus(String status) {
        this.model.purchaseOrderStatus = status;
    }

    @Override
    public void cancelOrderStatus(String status) {
        this.model.cancelOrderStatus = status;
    }
}
