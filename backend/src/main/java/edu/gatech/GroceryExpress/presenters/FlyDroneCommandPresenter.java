package edu.gatech.GroceryExpress.presenters;


import edu.gatech.GroceryExpress.interactors.responses.FlyDroneCommandResponse;
import edu.gatech.GroceryExpress.presenters.views.FlyDroneCommandView;
import edu.gatech.GroceryExpress.utility.GroceryExpressUtility;

public class FlyDroneCommandPresenter implements FlyDroneCommandResponse {
    private FlyDroneCommandView view;

    public FlyDroneCommandPresenter(FlyDroneCommandView view) {
        this.view = view;
    }

    @Override
    public void success(String language) {
        String status = GroceryExpressUtility.getStatus(language);
        view.flyDroneStatus(status);
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

        view.flyDroneStatus(error);
    }

    @Override
    public void droneError(String language) {
        String error = "";
        if (language.equals("spanish"))
            error = "ERROR:dron_identificador_no_existe";
        else if (language.equals("english"))
            error = "ERROR:drone_identifier_does_not_exist";
        else if (language.equals("russian"))
            error = "ОШИБКА:дрон_идентификатор_делает_нет_существовать";

        view.flyDroneStatus(error);
    }

    @Override
    public void pilotError(String language) {
        String error = "";
        if (language.equals("spanish"))
            error = "ERROR:piloto_identificador_no_existe";
        else if (language.equals("english"))
            error = "ERROR:pilot_identifier_does_not_exist";
        else if (language.equals("russian"))
            error = "ОШИБКА:пилот_идентификатор_делает_нет_существовать";

        view.flyDroneStatus(error);
    }
}
