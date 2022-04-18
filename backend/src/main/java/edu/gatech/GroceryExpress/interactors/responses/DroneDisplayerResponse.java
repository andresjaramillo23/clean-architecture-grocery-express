package edu.gatech.GroceryExpress.interactors.responses;


import edu.gatech.GroceryExpress.models.Drone;

import java.util.List;

public interface DroneDisplayerResponse {
    void displayDrones(String language, List<Drone> drones);

    void storeError(String language);
}
