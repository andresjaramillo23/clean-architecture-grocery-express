package edu.gatech.GroceryExpress.interactors;

import edu.gatech.GroceryExpress.GroceryExpress;
import edu.gatech.GroceryExpress.gateways.GatewayFactory;
import edu.gatech.GroceryExpress.gateways.GatewayRepository;
import edu.gatech.GroceryExpress.gateways.InMemoryGatewayRepository;
import edu.gatech.GroceryExpress.interactors.responses.OrderPurchaserResponse;
import edu.gatech.GroceryExpress.models.*;
import edu.gatech.GroceryExpress.services.requests.OrderPurchaserRequest;
import edu.gatech.GroceryExpress.utility.GroceryExpressUtility;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class OrderPurchaser implements OrderPurchaserRequest {
    private OrderPurchaserResponse response;
    private String store;
    private String order;
    private int totalCost = 0;
    private String droneId = "";
    private Pilot pilotWithExperience;
    private Customer customer;
    private String language;
    private InMemoryGatewayRepository gatewayFactory;

    public OrderPurchaser(OrderPurchaserResponse response, InMemoryGatewayRepository gatewayFactory) {
        this.response = Objects.requireNonNull(response);
        this.gatewayFactory = gatewayFactory;
    }

    @Override
    public void execute() {
        GatewayRepository gatewayRepository = gatewayFactory;
        List<Store> stores = gatewayRepository.retrieveStores();
        List<Customer> customers = gatewayRepository.retrieveCustomers();

        if (GroceryExpressUtility.isMissing(stores, store))
            response.storeError(language);
        else if (isMissingOrder(stores))
            response.orderError(language);
        else if (droneIsMissingPilot(stores))
            response.droneNeedsPilotError(language);
        else if (droneHasNotEnoughFuel(stores))
            response.droneFuelError(language);
        else {
            response.success(language);
            updateCustomerCredit(stores, customers);
            gatewayRepository.registerCustomer(customer);
            updateSystem(stores);
            gatewayRepository.registerStore(GroceryExpressUtility.getStore(stores, store));
            gatewayRepository.registerPilot(pilotWithExperience);
        }
    }

    @Override
    public void setLanguage(String language) {
        this.language = language;
    }

    private boolean isMissingOrder(List<Store> stores) {
        List<Order> orders = GroceryExpressUtility.getOrders(stores, this.store);
        return orders.stream().noneMatch(x -> x.getOrder().equals(this.order));
    }

    private boolean droneIsMissingPilot(List<Store> stores) {
        boolean missingPilot = true;
        droneId = getOrder(stores).getDrone();
        Drone drone = getDrone(stores, droneId);
        if (drone != null)
            if (drone.getPilot() != null)
                missingPilot = false;

        return missingPilot;
    }

    private boolean droneHasNotEnoughFuel(List<Store> stores) {
        int fuel = 0;
        String droneId = getOrder(stores).getDrone();
        Drone drone = getDrone(stores, droneId);
        if (drone != null)
            fuel = drone.getFuel();

        return fuel <= 0;
    }

    private void updateCustomerCredit(List<Store> stores, List<Customer> customers) {
        calculateTotalCost(getOrder(stores));
        customer = customers.stream().filter(x -> x.getAccount().equals(getOrder(stores).getCustomer())).findAny().orElse(null);
        if (customer != null) {
            int credit = customer.getCredit();
            customer.setCredit(credit - totalCost);
        }
    }

    private void calculateTotalCost(Order order) {
        List<Item> items = order.getItems();

        if (items != null) {
            for (Item item : items)
                totalCost += item.getQuantity() * item.getUnitPrice();
        }
    }

    private void updateSystem(List<Store> stores) {
        updateDroneFuel(stores);
        updateStore(stores);
    }

    private void updateStore(List<Store> stores) {
        Store store = GroceryExpressUtility.getStore(stores, this.store);
        store.getOrders().removeIf(c -> c.getOrder().equals(this.order));
        int revenue = store.getRevenue();
        store.setRevenue(revenue + totalCost);
    }

    private void updatePilotExperience(Drone drone) {
        Pilot pilot = drone.getPilot();
        pilot.setExperienceLevel(pilot.getExperienceLevel() + 1);

        pilotWithExperience = pilot;
    }

    private void updateDroneFuel(List<Store> stores) {
        Drone drone = getDrone(stores, droneId);
        updatePilotExperience(drone);
        drone.setFuel(drone.getFuel() - 1);
        Map<String, Integer> orderMap = drone.getOrderMap();

        orderMap.remove(order);
    }

    private Order getOrder(List<Store> stores) {
        List<Order> orders = GroceryExpressUtility.getOrders(stores, store);
        return orders.stream().filter(x -> x.getOrder().equals(this.order)).findAny().orElse(null);
    }

    private Drone getDrone(List<Store> stores, String droneId) {
        List<Drone> drones = GroceryExpressUtility.getDrones(stores, store);
        return drones.stream().filter(f -> f.getDrone().equals(droneId)).findAny().orElse(null);
    }

    @Override
    public void setStore(String store) {
        this.store = store;
    }

    @Override
    public void setOrder(String order) {
        this.order = order;
    }
}
