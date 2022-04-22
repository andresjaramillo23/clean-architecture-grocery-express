package edu.gatech.GroceryExpress.interactors;

import edu.gatech.GroceryExpress.GroceryExpress;
import edu.gatech.GroceryExpress.gateways.GatewayFactory;
import edu.gatech.GroceryExpress.gateways.GatewayRepository;
import edu.gatech.GroceryExpress.gateways.InMemoryGatewayRepository;
import edu.gatech.GroceryExpress.interactors.responses.ItemRequesterResponse;
import edu.gatech.GroceryExpress.models.*;
import edu.gatech.GroceryExpress.services.requests.ItemRequesterRequest;
import edu.gatech.GroceryExpress.utility.GroceryExpressUtility;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class ItemRequester implements ItemRequesterRequest {
    private ItemRequesterResponse response;
    private String store;
    private String order;
    private String item;
    private String quantity;
    private String unitPrice;
    private int weight = 0;
    private String language;
    private String currency;
    private InMemoryGatewayRepository gatewayFactory;

    public ItemRequester(ItemRequesterResponse response, InMemoryGatewayRepository gatewayFactory) {
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
        else if (isMissingItem(stores))
            response.missingItemError(language);
        else if (hasDuplicateItem(stores))
            response.itemDuplicateError(language);
        else if (customerCannotAffordOrder(customers, stores))
            response.noFundsCustomerError(language);
        else if (droneCannotCarryItem(stores))
            response.droneCapacityError(language);
        else {
            response.success(language);
            updateStore(stores);
            gatewayRepository.registerStore(GroceryExpressUtility.getStore(stores, store));
        }
    }

    @Override
    public void setLanguage(String language) {
        this.language = language;
    }

    private void updateStore(List<Store> stores) {
        updateOrder(stores);
        updateDrone(stores);
    }

    private void updateDrone(List<Store> stores) {
        Drone drone = getDrone(stores);
        int currentItemWeight = weight * Integer.parseInt(quantity);
        Map<String, Integer> orderMap = drone.getOrderMap();
        int orderWeight = 0;
        if (!orderMap.isEmpty())
            if (orderMap.get(this.order) != null)
                orderWeight = orderMap.get(this.order);

        drone.addOrders(this.order, orderWeight + currentItemWeight);
    }

    private void updateOrder(List<Store> stores) {
        Order order = getOrder(stores);
        Item item = new Item(this.item, this.weight, currency);
        item.setQuantity(Integer.parseInt(this.quantity));
        item.setUnitPrice(Integer.parseInt(this.unitPrice));

        if (order != null)
            order.addItems(item);
    }

    private boolean droneCannotCarryItem(List<Store> stores) {
        int droneCapacity = getDroneCapacity(stores);
        getItemWeight(stores);
        int totalWeightOfItemsAlreadyInTheOrder = getTotalWeightOfItemsAlreadyInTheOrder(getOrder(stores).getItems());
        int currentItemWeight = weight * Integer.parseInt(this.quantity);

        return droneCapacity < currentItemWeight + totalWeightOfItemsAlreadyInTheOrder;
    }

    private int getDroneCapacity(List<Store> stores) {
        Drone drone = getDrone(stores);
        int capacity = 0;
        if (drone != null)
            capacity = drone.getCapacity();
        return capacity;
    }

    private int getTotalWeightOfItemsAlreadyInTheOrder(List<Item> itemsAlreadyOrdered) {
        int totalWeight = 0;
        if (itemsAlreadyOrdered != null)
            for (Item item : itemsAlreadyOrdered) {
                totalWeight += item.getWeight() * item.getQuantity();
            }
        return totalWeight;
    }

    private void getItemWeight(List<Store> stores) {
        List<Item> items = GroceryExpressUtility.getItems(stores, store);
        if (!items.isEmpty()) {
            Item item = items.stream().filter(h -> h.getName().equals(this.item)).findAny().orElse(null);
            if (item != null)
                weight = item.getWeight();
        }
    }

    private boolean customerCannotAffordOrder(List<Customer> customers, List<Store> stores) {
        boolean isAffordable = false;
        Order order = getOrder(stores);
        if (getOrder(stores) != null)
            isAffordable = isAffordable(customers, order);

        return isAffordable;
    }

    private boolean isAffordable(List<Customer> customers, Order order) {
        int totalPrice = getTotalPriceForItemsInOrder(order.getItems());
        int credit = getCredit(customers, order);
        int totalCostCurrentItem = (Integer.parseInt(this.quantity) * Integer.parseInt(this.unitPrice));
        return credit < (totalCostCurrentItem + totalPrice);
    }

    private int getTotalPriceForItemsInOrder(List<Item> items) {
        int totalPrice = 0;
        if (items != null)
            for (Item item : items) {
                totalPrice += item.getUnitPrice() * item.getQuantity();
            }
        return totalPrice;
    }

    private int getCredit(List<Customer> customers, Order order) {
        Customer customer = customers.stream().filter(y -> y.getAccount().equals(order.getCustomer())).findAny().orElse(null);
        int credit = customer.getCredit();
        return credit;
    }

    private boolean hasDuplicateItem(List<Store> stores) {
        List<Order> orders = GroceryExpressUtility.getOrders(stores, this.store);
        List<Item> items = orders.stream().filter(x -> x.getOrder().equals(this.order)).flatMap(x -> x.getItems().stream()).collect(Collectors.toList());

        return isDuplicated(items);
    }

    private boolean isDuplicated(List<Item> items) {
        boolean isDuplicated = false;
        for (Item item : items)
            if (this.item.equals(item.getName()))
                isDuplicated = true;
        return isDuplicated;
    }

    private boolean isMissingItem(List<Store> stores) {
        List<Item> items = GroceryExpressUtility.getItems(stores, store);
        return items.stream().noneMatch(x -> x.getName().equals(this.item));
    }

    private boolean isMissingOrder(List<Store> stores) {
        List<Order> orders = GroceryExpressUtility.getOrders(stores, this.store);
        return orders.stream().noneMatch(x -> x.getOrder().equals(this.order));
    }

    private Order getOrder(List<Store> stores) {
        List<Order> orders = GroceryExpressUtility.getOrders(stores, store);
        return orders.stream().filter(x -> x.getOrder().equals(this.order)).findAny().orElse(null);
    }

    private Drone getDrone(List<Store> stores) {
        Order order = getOrder(stores);
        List<Drone> drones = GroceryExpressUtility.getDrones(stores, store);

        return drones.stream().filter(f -> f.getDrone().equals(order.getDrone())).findAny().orElse(null);
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
    public void setItem(String item) {
        this.item = item;
    }

    @Override
    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    @Override
    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
