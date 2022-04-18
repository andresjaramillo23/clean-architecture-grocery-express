package edu.gatech.GroceryExpress.services.requests;

public interface DroneMakerRequest extends Request {
    void setStore(String store);

    void setDrone(String droneIdentifier);

    void setCapacity(String weightCapacity);

    void setFuel(String numberOfDeliveries);
}
