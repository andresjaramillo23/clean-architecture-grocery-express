package edu.gatech.GroceryExpress.interactors;

import edu.gatech.GroceryExpress.GroceryExpress;
import edu.gatech.GroceryExpress.gateways.GatewayFactory;
import edu.gatech.GroceryExpress.gateways.GatewayRepository;
import edu.gatech.GroceryExpress.gateways.InMemoryGatewayRepository;
import edu.gatech.GroceryExpress.interactors.responses.OrderStarterResponse;
import edu.gatech.GroceryExpress.models.Customer;
import edu.gatech.GroceryExpress.models.Drone;
import edu.gatech.GroceryExpress.models.Order;
import edu.gatech.GroceryExpress.models.Store;
import edu.gatech.GroceryExpress.services.requests.OrderStarterRequest;
import edu.gatech.GroceryExpress.utility.GroceryExpressUtility;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

public class OrderStarter implements OrderStarterRequest {
    private OrderStarterResponse response;
    private String store;
    private String order;
    private String drone;
    private String customer;
    private String language;
    private InMemoryGatewayRepository gatewayFactory;

    public OrderStarter(OrderStarterResponse response, InMemoryGatewayRepository gatewayFactory) {
        this.response = Objects.requireNonNull(response);
        this.gatewayFactory = gatewayFactory;
    }

    @Override
    public void execute() {
        GatewayRepository gatewayRepository = gatewayFactory;
        List<Store> stores = new ArrayList<>(gatewayRepository.retrieveStores());
        List<Customer> customers = new ArrayList<>(gatewayRepository.retrieveCustomers());

        if (GroceryExpressUtility.isMissing(stores, this.store))
            response.storeError(language);
        else if (hasDuplicateOrder(stores))
            response.orderError(language);
        else if (isMissingDroneIdentifier(stores))
            response.droneIdentifierError(language);
        else if (isMissingCustomer(customers))
            response.customerAccountError(language);
        else {
            updateSystem(stores);
            gatewayRepository.registerStore(GroceryExpressUtility.getStore(stores, store));
            response.success(language);
        }
    }

    @Override
    public void setLanguage(String language) {
        this.language = language;
    }

    private void updateSystem(List<Store> stores) {
        updateDrone(stores);
        updateStore(stores);
    }

    private void updateDrone(List<Store> stores) {
        List<Drone> drones = GroceryExpressUtility.getDrones(stores, this.store);

        Drone drone = drones.stream().filter(f -> f.getDrone().equals(this.drone)).findAny().orElse(null);
        if (drone != null)
            drone.addOrders(order, 0);
    }

    private void updateStore(List<Store> stores) {
        Store store = GroceryExpressUtility.getStore(stores, this.store);
        if (store != null)
            store.addOrders(new Order(getProperties()));
    }

    private boolean isMissingCustomer(List<Customer> customers) {
        return customers.stream().noneMatch(x -> x.getAccount().equals(customer));
    }

    private boolean hasDuplicateOrder(List<Store> stores) {
        List<Order> orders = GroceryExpressUtility.getOrders(stores, this.store);
        return orders.stream().anyMatch(x -> x.getOrder().equals(order));
    }

    private boolean isMissingDroneIdentifier(List<Store> stores) {
        List<Drone> drones = GroceryExpressUtility.getDrones(stores, this.store);
        return drones.stream().noneMatch(x -> x.getDrone().equals(drone));
    }

    @Override
    public void setStore(String store) {
        this.store = store;
    }

    @Override
    public void setOrder(String order) {
        this.order = order;
    }

    @Override
    public void setDrone(String drone) {
        this.drone = drone;
    }

    @Override
    public void setCustomer(String customer) {
        this.customer = customer;
    }

    private Properties getProperties() {
        Properties properties = new Properties();
        properties.setProperty("store", store);
        properties.setProperty("order", order);
        properties.setProperty("drone", drone);
        properties.setProperty("customer", customer);

        return properties;
    }
}
