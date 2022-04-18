package edu.gatech.GroceryExpress.interactors;

import edu.gatech.GroceryExpress.GroceryExpress;
import edu.gatech.GroceryExpress.gateways.GatewayFactory;
import edu.gatech.GroceryExpress.gateways.GatewayRepository;
import edu.gatech.GroceryExpress.interactors.responses.PilotDisplayerResponse;
import edu.gatech.GroceryExpress.models.Pilot;
import edu.gatech.GroceryExpress.services.requests.PilotDisplayerRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PilotDisplayer implements PilotDisplayerRequest {
    private PilotDisplayerResponse response;
    private String language;
    private GatewayFactory gatewayFactory;

    public PilotDisplayer(PilotDisplayerResponse response, GatewayFactory gatewayFactory) {
        this.response = Objects.requireNonNull(response);
        this.gatewayFactory = gatewayFactory;
    }

    @Override
    public void execute() {
        GatewayRepository gatewayRepository = gatewayFactory.createGatewayRepository();

        List<Pilot> pilots = new ArrayList<>(gatewayRepository.retrievePilots());

        response.displayPilots(language, pilots);
    }

    @Override
    public void setLanguage(String language) {
        this.language = language;
    }
}
