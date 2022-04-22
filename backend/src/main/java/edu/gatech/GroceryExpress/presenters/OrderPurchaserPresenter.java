package edu.gatech.GroceryExpress.presenters;


import edu.gatech.GroceryExpress.interactors.responses.OrderPurchaserResponse;
import edu.gatech.GroceryExpress.presenters.views.OrderPurchaserView;
import edu.gatech.GroceryExpress.utility.GroceryExpressUtility;

public class OrderPurchaserPresenter implements OrderPurchaserResponse {
    private OrderPurchaserView view;

    public OrderPurchaserPresenter(OrderPurchaserView view) {
        this.view = view;
    }

    @Override
    public void success(String language) {
        String status = GroceryExpressUtility.getStatus(language);
        view.orderPurchaserStatus(status);
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

        view.orderPurchaserStatus(error);
    }

    @Override
    public void orderError(String language) {
        String error = "";
        if (language.equals("spanish"))
            error = "ERROR:orden_identificador_no_existe";
        else if (language.equals("english"))
            error = "ERROR:order_identifier_does_not_exist";
        else if (language.equals("russian"))
            error = "ОШИБКА:порядок_идентификатор_делает_нет_существовать";

        view.orderPurchaserStatus(error);
    }

    @Override
    public void droneFuelError(String language) {
        String error = "";
        if (language.equals("spanish"))
            error = "ERROR:dron_necesita_combustible";
        else if (language.equals("english"))
            error = "ERROR:drone_needs_fuel";
        else if (language.equals("russian"))
            error = "ОШИБКА:дрон_нужно_топливо";

        view.orderPurchaserStatus(error);
    }

    @Override
    public void droneNeedsPilotError(String language) {
        String error = "";
        if (language.equals("spanish"))
            error = "ERROR:dron_necesita_piloto";
        else if (language.equals("english"))
            error = "ERROR:drone_needs_pilot";
        else if (language.equals("russian"))
            error = "ОШИБКА:дрон_нужно_пилот";

        view.orderPurchaserStatus(error);
    }
}
