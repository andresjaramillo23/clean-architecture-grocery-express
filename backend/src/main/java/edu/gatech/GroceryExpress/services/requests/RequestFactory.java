package edu.gatech.GroceryExpress.services.requests;


import edu.gatech.GroceryExpress.interactors.responses.*;

public interface RequestFactory {
    StoreMakerRequest makeStore(StoreMakerResponse response);

    StoreDisplayerRequest displayStores(StoreDisplayerResponse response);

    ItemSellerRequest sellItem(ItemSellerResponse response);

    ItemDisplayerRequest displayItem(ItemDisplayerResponse response);

    PilotMakerRequest makePilot(PilotMakerResponse response);

    PilotDisplayerRequest displaypilots(PilotDisplayerResponse response);

    DroneMakerRequest makeDrone(DroneMakerResponse response);

    DroneDisplayerRequest displayDrones(DroneDisplayerResponse response);

    FlyDroneCommandRequest flyDrone(FlyDroneCommandResponse response);

    CustomerMakerRequest makeCustomer(CustomerMakerResponse response);

    CustomerDisplayerRequest displayCustomer(CustomerDisplayerResponse response);

    OrderStarterRequest startOrder(OrderStarterResponse response);

    OrderDisplayerRequest displayOrders(OrderDisplayerResponse response);

    ItemRequesterRequest requestItem(ItemRequesterResponse response);

    OrderPurchaserRequest purchaseOrder(OrderPurchaserResponse response);

    OrderCancellerRequest cancelOrder(OrderCancellerResponse response);
}
