package edu.gatech.GroceryExpress.presenters;


import edu.gatech.GroceryExpress.interactors.responses.DroneDisplayerResponse;
import edu.gatech.GroceryExpress.models.Drone;
import edu.gatech.GroceryExpress.presenters.views.DroneDisplayerView;

import java.util.List;
import java.util.Map;

public class DroneDisplayerPresenter implements DroneDisplayerResponse {
    private DroneDisplayerView view;

    public DroneDisplayerPresenter(DroneDisplayerView view) {
        this.view = view;
    }

    @Override
    public void displayDrones(String language, List<Drone> drones) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Drone drone : drones) {
            Map<String, Integer> orderMap = drone.getOrderMap();
            int totalWeight = orderMap.values().stream().mapToInt(x -> x).sum();
            int remainingCapacity = drone.getCapacity() - totalWeight;

            if (language.equals("english"))
                (stringBuilder.append("droneID:").append(drone.getDrone()).append(",")
                    .append("total_cap:").append(drone.getCapacity()).append(",")
                    .append("num_orders:").append(orderMap.size()).append(",")
                    .append("remaining_cap:")).append(remainingCapacity).append(",")
                    .append("trips_left:").append(drone.getFuel());
            else if (language.equals("spanish"))
                (stringBuilder.append("dronID:").append(drone.getDrone()).append(",")
                        .append("total_cap:").append(drone.getCapacity()).append(",")
                        .append("num_orders:").append(orderMap.size()).append(",")
                        .append("restante_cap:")).append(remainingCapacity).append(",")
                        .append("viajes_restantes:").append(drone.getFuel());
            else if (language.equals("russian"))
                (stringBuilder.append("дронID:").append(drone.getDrone()).append(",")
                        .append("общее_вмес:").append(drone.getCapacity()).append(",")
                        .append("кол_заказы:").append(orderMap.size()).append(",")
                        .append("оставшаяся_емк:")).append(remainingCapacity).append(",")
                        .append("осталось_поездок:").append(drone.getFuel());

            if (drone.getPilot() != null) {
                if (language.equals("english"))
                    stringBuilder.append(",").append("flown_by:").append(drone.getPilot().getFirstName()).append("_").append(drone.getPilot().getLastName()).append(System.lineSeparator());
                else if (language.equals("spanish"))
                    stringBuilder.append(",").append("volado_por:").append(drone.getPilot().getFirstName()).append("_").append(drone.getPilot().getLastName()).append(System.lineSeparator());
                else if (language.equals("russian"))
                    stringBuilder.append(",").append("пролетел_мимо:").append(drone.getPilot().getFirstName()).append("_").append(drone.getPilot().getLastName()).append(System.lineSeparator());
            }
            else
                stringBuilder.append(System.lineSeparator());
        }

        if (language.equals("english"))
            stringBuilder.append("OK:display_completed");
        else if (language.equals("spanish"))
            stringBuilder.append("OK:muestra_completada");
        else if (language.equals("russian"))
            stringBuilder.append("OK:отображать_завершенный");

        view.displayDrones(stringBuilder.toString());
    }

    @Override
    public void storeError(String language) {
        String error = "";
        if (language.equals("spanish"))
            error = "ERROR:tienda_identificador_no_existe";
        else if (language.equals("english"))
            error = "ERROR:store_identifier_does_not_exist";
        else if (language.equals("russian"))
            error = "ОШИБКА:хранить_идентификатор_делает_нет_существовать";

        view.displayDrones(error);
    }
}
