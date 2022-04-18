package edu.gatech.GroceryExpress.models;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Drone {
    private String store;
    private String drone;
    private int capacity;
    private int fuel;
    private Pilot pilot;
    private Map<String, Integer> orders = new HashMap<>();

    public Drone(Properties properties) {
        this.store = properties.getProperty("store");
        this.drone = properties.getProperty("drone");
        this.capacity = Integer.parseInt(properties.getProperty("capacity"));
        this.fuel = Integer.parseInt(properties.getProperty("fuel"));
    }

    public String getStore() {
        return store;
    }

    public String getDrone() {
        return drone;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getFuel() {
        return fuel;
    }

    public Pilot getPilot() {
        return pilot;
    }

    public void setPilot(Pilot pilot) {
        this.pilot = pilot;
    }

    public void setFuel(int fuel) {
        this.fuel = fuel;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public void setDrone(String drone) {
        this.drone = drone;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Map<String, Integer> getOrderMap() {
        return orders;
    }

    public void addOrders(String order, int quantity) {
        orders.put(order, quantity);
    }
}
