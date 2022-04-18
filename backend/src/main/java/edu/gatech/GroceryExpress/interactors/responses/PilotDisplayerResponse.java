package edu.gatech.GroceryExpress.interactors.responses;

import edu.gatech.GroceryExpress.models.Pilot;

import java.util.List;

public interface PilotDisplayerResponse {
    void displayPilots(String language, List<Pilot> pilots);
}
