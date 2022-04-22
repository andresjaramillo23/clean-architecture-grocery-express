package edu.gatech.GroceryExpress.presenters.models;


import edu.gatech.GroceryExpress.models.Customer;
import edu.gatech.GroceryExpress.models.Pilot;
import edu.gatech.GroceryExpress.models.Store;

import java.util.ArrayList;
import java.util.List;

public class DeliveryServiceViewModel {
    public String displayStores = "";
    public String makeStoreStatus = "";
    public String sellItemStatus = "";
    public String displayItems = "";
    public String makePilotStatus = "";
    public String displayPilots = "";
    public String makeDroneStatus = "";
    public String displayDrones = "";
    public String flyDroneStatus = "";
    public String makeCustomerStatus = "";
    public String displayCustomers = "";
    public String startOrderStatus = "";
    public String displayOrders = "";
    public String requestItemStatus = "";
    public String purchaseOrderStatus = "";
    public String cancelOrderStatus = "";
    public List<Customer> customers = new ArrayList<>();
    public List<Store> stores = new ArrayList<>();
    public List<Pilot> pilots = new ArrayList<>();
}
