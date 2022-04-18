package edu.gatech.GroceryExpress.services.requests;

public interface FlyDroneCommandRequest extends Request {
    void setStore(String store);

    void setDrone(String drone);

    void setPilotAccount(String pilotAccount);
}
