package edu.gatech.GroceryExpress.services;

import edu.gatech.GroceryExpress.interactors.responses.StoreMakerResponse;
import edu.gatech.GroceryExpress.presenters.StoreDisplayerPresenter;
import edu.gatech.GroceryExpress.presenters.StoreMakerPresenter;
import edu.gatech.GroceryExpress.presenters.models.DeliveryServiceViewModel;
import edu.gatech.GroceryExpress.presenters.views.*;
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

    public void processFile(MultipartFile file) {
        try {
            List<String> lines = extractLines(file);
            lines.forEach(line -> {
                if (line.contains("//")) {
                    System.out.println("> " + line);
                } else {
                    String[] input = line.split(WORD_DELIMITER);

                    switch (input[0]) {
                        case "make_store":
                            StoreMakerResponse storeMakerPresenter = new StoreMakerPresenter(this);
                            Properties properties = new Properties();
                            properties.setProperty("store", input[1]);
                            properties.setProperty("revenue", input[2]);
                            deliveryService.makeStoreRequest("", "", properties, storeMakerPresenter);
                            System.out.println(this.model.makeStoreStatus);
                            break;
                        case "display_stores":
                            StoreDisplayerPresenter storeDisplayerPresenter = new StoreDisplayerPresenter(this);
                            deliveryService.displayStoresRequest("", "", storeDisplayerPresenter);
                            System.out.println(this.model.displayStores);
                            break;
                        case "sell_item":
                            System.out.println("will display stores");
                            break;
                        case "display_items":
                            System.out.println("will display stores");
                            break;
                        case "make_pilot":
                            System.out.println("will display stores");
                            break;
                        case "display_pilots":
                            System.out.println("will display stores");
                            break;
                        case "make_drone":
                            System.out.println("will display stores");
                            break;
                        case "display_drones":
                            System.out.println("will display stores");
                            break;
                        case "fly_drone":
                            System.out.println("will display stores");
                            break;
                        case "make_customer":
                            System.out.println("will display stores");
                            break;
                        case "display_customers":
                            System.out.println("will display stores");
                            break;
                        case "start_order":
                            System.out.println("will display stores");
                            break;
                        case "display_orders":
                            System.out.println("will display stores");
                            break;
                        case "request_item":
                            System.out.println("will display stores");
                            break;
                        case "cancel_order":
                            System.out.println("will display stores");
                            break;
                        case "stop":
                            System.out.println("stop acknowledged");
                            break;
                    }
                }
            });

        } catch (Exception e) {
            System.out.println("Could not read file. Cause: " + e.getMessage());
        }
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
