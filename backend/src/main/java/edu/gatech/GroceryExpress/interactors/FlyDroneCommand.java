package edu.gatech.GroceryExpress.interactors;

import edu.gatech.GroceryExpress.GroceryExpress;
import edu.gatech.GroceryExpress.gateways.GatewayFactory;
import edu.gatech.GroceryExpress.gateways.GatewayRepository;
import edu.gatech.GroceryExpress.interactors.responses.FlyDroneCommandResponse;
import edu.gatech.GroceryExpress.models.Drone;
import edu.gatech.GroceryExpress.models.Pilot;
import edu.gatech.GroceryExpress.models.Store;
import edu.gatech.GroceryExpress.services.requests.FlyDroneCommandRequest;
import edu.gatech.GroceryExpress.utility.GroceryExpressUtility;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FlyDroneCommand implements FlyDroneCommandRequest {
    private FlyDroneCommandResponse response;
    private String store;
    private String drone;
    private String pilotAccount;
    private String language;
    private GatewayFactory gatewayFactory;

    public FlyDroneCommand(FlyDroneCommandResponse response, GatewayFactory gatewayFactory) {
        this.response = Objects.requireNonNull(response);
        this.gatewayFactory = gatewayFactory;
    }

    @Override
    public void execute() {
        GatewayRepository gatewayRepository = gatewayFactory.createGatewayRepository();
        List<Store> stores = new ArrayList<>(gatewayRepository.retrieveStores());
        List<Pilot> pilots = new ArrayList<>(gatewayRepository.retrievePilots());

        if (GroceryExpressUtility.isMissing(stores, this.store))
            response.storeError(language);
        else if (isMissingDroneIdentifier(stores))
            response.droneError(language);
        else if (isMissingPilot(pilots))
            response.pilotError(language);
        else {
            response.success(language);
            updateStore(stores, getPilot(pilots));
            gatewayRepository.registerStore(GroceryExpressUtility.getStore(stores, this.store));
        }
    }

    @Override
    public void setLanguage(String language) {
        this.language = language;
    }

    private void updateStore(List<Store> stores, Pilot pilot) {
        removePilotFromPreviousDrone(stores);
        assignPilotTDrone(stores, pilot);
    }

    private Drone getDroneToBeAssignedTo(List<Store> stores) {
        return GroceryExpressUtility.getDrones(stores, this.store).stream().filter(f -> f.getDrone().equals(drone)).findAny().orElse(null);
    }

    private void assignPilotTDrone(List<Store> stores, Pilot pilot) {
        Drone newDrone = getDroneToBeAssignedTo(stores);
        newDrone.setPilot(pilot);
    }

    private void removePilotFromPreviousDrone(List<Store> stores) {
        for (Drone drone : GroceryExpressUtility.getDrones(stores, this.store))
            if (drone != null)
                if (drone.getPilot() != null)
                    if (drone.getPilot().getAccount().equals(pilotAccount))
                        drone.setPilot(null);
    }

    private Pilot getPilot(List<Pilot> pilots) {
        return pilots.stream().filter(x -> x.getAccount().equals(this.pilotAccount)).findAny().orElse(null);
    }

    private boolean isMissingPilot(List<Pilot> pilots) {
        return pilots.stream().noneMatch(x -> x.getAccount().equals(pilotAccount));
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
    public void setDrone(String drone) {
        this.drone = drone;
    }

    @Override
    public void setPilotAccount(String pilotAccount) {
        this.pilotAccount = pilotAccount;
    }
}
