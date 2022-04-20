package edu.gatech.GroceryExpress.presenters;


import edu.gatech.GroceryExpress.interactors.responses.DroneMakerResponse;
import edu.gatech.GroceryExpress.presenters.views.DroneMakerView;
import edu.gatech.GroceryExpress.utility.GroceryExpressUtility;

public class DroneMakerPresenter implements DroneMakerResponse {
    private DroneMakerView view;

    public DroneMakerPresenter(DroneMakerView view) {
        this.view = view;
    }

    @Override
    public void success(String language) {
        String status = GroceryExpressUtility.getStatus(language);
        view.makeDroneStatus(status);
    }

    @Override
    public void droneIdentifierError(String language) {
        String error = "";
        if (language.equals("spanish"))
            error = "ERROR:dron_identificador_no_existe";
        else if (language.equals("english"))
            error = "ERROR:drone_identifier_already_exists";
        else if (language.equals("russian"))
            error = "ОШИБКА:дрон_идентификатор_делает_нет_существовать";

        view.makeDroneStatus(error);
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

        view.makeDroneStatus(error);
    }
}
