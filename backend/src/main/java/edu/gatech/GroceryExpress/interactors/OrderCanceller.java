package edu.gatech.GroceryExpress.interactors;

import edu.gatech.GroceryExpress.GroceryExpress;
import edu.gatech.GroceryExpress.gateways.GatewayFactory;
import edu.gatech.GroceryExpress.gateways.GatewayRepository;
import edu.gatech.GroceryExpress.interactors.responses.OrderCancellerResponse;
import edu.gatech.GroceryExpress.models.Drone;
import edu.gatech.GroceryExpress.models.Order;
import edu.gatech.GroceryExpress.models.Store;
import edu.gatech.GroceryExpress.services.requests.OrderCancellerRequest;
import edu.gatech.GroceryExpress.utility.GroceryExpressUtility;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class OrderCanceller implements OrderCancellerRequest {
    private OrderCancellerResponse response;
    private String store;
    private String order;
    private String language;
    private GatewayFactory gatewayFactory;

    public OrderCanceller(OrderCancellerResponse response, GatewayFactory gatewayFactory) {
        this.response = Objects.requireNonNull(response);
        this.gatewayFactory = gatewayFactory;
    }

    @Override
    public void execute() {
        GatewayRepository gatewayRepository = gatewayFactory.createGatewayRepository();
        List<Store> stores = gatewayRepository.retrieveStores();

        if (GroceryExpressUtility.isMissing(stores, store))
            response.storeError(language);
        else if (isMissingOrder(stores))
            response.orderError(language);
        else {
            response.success(language);
            updateSystem(stores);
            gatewayRepository.registerStore(GroceryExpressUtility.getStore(stores, store));
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

    private Drone getDrone(List<Store> stores) {
        List<Drone> drones = GroceryExpressUtility.getDrones(stores, store);
        return drones.stream().filter(f -> f.getDrone().equals(getDroneId(stores))).findAny().orElse(null);
    }

    private void updateDrone(List<Store> stores) {
        Drone drone = getDrone(stores);
        Map<String, Integer> orderMap = drone.getOrderMap();
        orderMap.remove(order);
    }

    private String getDroneId(List<Store> stores) {
        return getOrder(stores).getDrone();
    }

    private Order getOrder(List<Store> stores) {
        List<Order> orders = GroceryExpressUtility.getOrders(stores, store);
        return orders.stream().filter(x -> x.getOrder().equals(this.order)).findAny().orElse(null);
    }

    private boolean isMissingOrder(List<Store> stores) {
        List<Order> orders = GroceryExpressUtility.getOrders(stores, this.store);
        return orders.stream().noneMatch(x -> x.getOrder().equals(this.order));
    }

    private void updateStore(List<Store> stores) {
        Store store = stores.stream().filter(x -> x.getStore().equals(this.store)).findFirst().orElse(null);

        if (store != null)
            if (store.getOrders() != null)
                store.getOrders().removeIf(f -> f.getOrder().equals(this.order));
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
