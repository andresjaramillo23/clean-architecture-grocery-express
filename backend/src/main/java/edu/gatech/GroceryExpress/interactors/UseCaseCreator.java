package edu.gatech.GroceryExpress.interactors;

import edu.gatech.GroceryExpress.gateways.GatewayFactoryImplementation;
import edu.gatech.GroceryExpress.gateways.InMemoryGatewayRepository;
import edu.gatech.GroceryExpress.interactors.responses.*;
import edu.gatech.GroceryExpress.services.requests.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class UseCaseCreator implements RequestFactory {

//    GatewayFactoryImplementation gatewayFactory = new GatewayFactoryImplementation();
    @Autowired
    InMemoryGatewayRepository gatewayFactory;

    @Override
    public StoreMakerRequest makeStore(StoreMakerResponse response) {
        return new StoreMaker(response, gatewayFactory);
    }

    @Override
    public StoreDisplayerRequest displayStores(StoreDisplayerResponse response) {
        return new StoreDisplayer(response, gatewayFactory);
    }

    @Override
    public ItemSellerRequest sellItem(ItemSellerResponse response) {
        return new ItemSeller(response, gatewayFactory);
    }

    @Override
    public ItemDisplayerRequest displayItem(ItemDisplayerResponse response) {
        return new ItemDisplayer(response, gatewayFactory);
    }

    @Override
    public PilotMakerRequest makePilot(PilotMakerResponse response) {
        return new PilotMaker(response, gatewayFactory);
    }

    @Override
    public PilotDisplayerRequest displaypilots(PilotDisplayerResponse response) {
        return new PilotDisplayer(response, gatewayFactory);
    }

    @Override
    public DroneMakerRequest makeDrone(DroneMakerResponse response) {
        return new DroneMaker(response, gatewayFactory);
    }

    @Override
    public DroneDisplayerRequest displayDrones(DroneDisplayerResponse response) {
        return new DroneDisplayer(response, gatewayFactory);
    }

    @Override
    public FlyDroneCommandRequest flyDrone(FlyDroneCommandResponse response) {
        return new FlyDroneCommand(response, gatewayFactory);
    }

    @Override
    public CustomerMakerRequest makeCustomer(CustomerMakerResponse response) {
        return new CustomerMaker(response, gatewayFactory);
    }

    @Override
    public CustomerDisplayerRequest displayCustomer(CustomerDisplayerResponse response) {
        return new CustomerDisplayer(response, gatewayFactory);
    }

    @Override
    public OrderStarterRequest startOrder(OrderStarterResponse response) {
        return new OrderStarter(response, gatewayFactory);
    }

    @Override
    public OrderDisplayerRequest displayOrders(OrderDisplayerResponse response) {
        return new OrderDisplayer(response, gatewayFactory);
    }

    @Override
    public ItemRequesterRequest requestItem(ItemRequesterResponse response) {
        return new ItemRequester(response, gatewayFactory);
    }

    @Override
    public OrderPurchaserRequest purchaseOrder(OrderPurchaserResponse response) {
        return new OrderPurchaser(response, gatewayFactory);
    }

    @Override
    public OrderCancellerRequest cancelOrder(OrderCancellerResponse response) {
        return new OrderCanceller(response, gatewayFactory);
    }
}
