package edu.gatech.GroceryExpress.presenters;


import edu.gatech.GroceryExpress.interactors.responses.PilotMakerResponse;
import edu.gatech.GroceryExpress.presenters.views.PilotMakerView;
import edu.gatech.GroceryExpress.utility.GroceryExpressUtility;

public class PilotMakerPresenter implements PilotMakerResponse {
    private PilotMakerView view;

    public PilotMakerPresenter(PilotMakerView view) {
        this.view = view;
    }

    @Override
    public void success(String language) {
        String status = GroceryExpressUtility.getStatus(language);
        view.makePilotStatus(status);
    }

    @Override
    public void accountError(String language) {
        String error = "";
        if (language.equals("spanish"))
            error = "ERROR:piloto_identificador_ya_existe";
        else if (language.equals("english"))
            error = "ERROR:pilot_identifier_already_exists";
        else if (language.equals("russian"))
            error = "ОШИБКА:пилот_идентификатор_уже_существуют";

        view.makePilotStatus(error);
    }

    @Override
    public void pilotLicenseError(String language) {
        String error = "";
        if (language.equals("spanish"))
            error = "ERROR:piloto_licensia_ya_existe";
        else if (language.equals("english"))
            error = "ERROR:pilot_license_already_exists";
        else if (language.equals("russian"))
            error = "ОШИБКА:пилот_лицензия_уже_существуют";

        view.makePilotStatus(error);
    }
}
