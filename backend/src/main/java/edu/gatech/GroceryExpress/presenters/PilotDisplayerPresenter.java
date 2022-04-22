package edu.gatech.GroceryExpress.presenters;


import edu.gatech.GroceryExpress.interactors.responses.PilotDisplayerResponse;
import edu.gatech.GroceryExpress.models.Pilot;
import edu.gatech.GroceryExpress.presenters.models.DeliveryServiceViewModel;
import edu.gatech.GroceryExpress.presenters.views.PilotDisplayerView;

import java.util.List;

public class PilotDisplayerPresenter implements PilotDisplayerResponse {
    private PilotDisplayerView view;

    public PilotDisplayerPresenter(PilotDisplayerView view) {
        this.view = view;
    }

    @Override
    public void displayPilots(String language, List<Pilot> pilots) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Pilot pilot : pilots) {
            if (language.equals("english"))
                (stringBuilder.append("name:").append(pilot.getFirstName()).append("_").append(pilot.getLastName()).append(",")
                        .append("phone:").append(pilot.getPhoneNumber()).append(",")
                        .append("taxID:")).append(pilot.getTaxId()).append(",")
                        .append("licenseID:").append(pilot.getLicenseId()).append(",")
                        .append("experience:").append(pilot.getExperienceLevel()).append(System.lineSeparator());
            else if (language.equals("spanish"))
                (stringBuilder.append("nombre:").append(pilot.getFirstName()).append("_").append(pilot.getLastName()).append(",")
                        .append("telefono:").append(pilot.getPhoneNumber()).append(",")
                        .append("taxID:")).append(pilot.getTaxId()).append(",")
                        .append("licensiaID:").append(pilot.getLicenseId()).append(",")
                        .append("experiencia:").append(pilot.getExperienceLevel()).append(System.lineSeparator());
            else if (language.equals("russian"))
                (stringBuilder.append("название:").append(pilot.getFirstName()).append("_").append(pilot.getLastName()).append(",")
                        .append("Телефон:").append(pilot.getPhoneNumber()).append(",")
                        .append("taxID:")).append(pilot.getTaxId()).append(",")
                        .append("лицензияID:").append(pilot.getLicenseId()).append(",")
                        .append("опыт:").append(pilot.getExperienceLevel()).append(System.lineSeparator());
        }

        if (language.equals("english"))
            stringBuilder.append("OK:display_completed");
        else if (language.equals("spanish"))
            stringBuilder.append("OK:muestra_completada");
        else if (language.equals("russian"))
            stringBuilder.append("OK:отображать_завершенный");

        DeliveryServiceViewModel model = new DeliveryServiceViewModel();
        model.pilots = pilots;
        model.displayPilots = stringBuilder.toString();

        view.displayPilots(model);
    }
}
