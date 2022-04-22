package edu.gatech.GroceryExpress.presenters;


import edu.gatech.GroceryExpress.interactors.responses.OrderStarterResponse;
import edu.gatech.GroceryExpress.presenters.views.OrderStarterView;
import edu.gatech.GroceryExpress.utility.GroceryExpressUtility;

public class OrderStarterPresenter implements OrderStarterResponse {
    private OrderStarterView view;

    public OrderStarterPresenter(OrderStarterView view) {
        this.view = view;
    }

    @Override
    public void success(String language) {
        String status = GroceryExpressUtility.getStatus(language);
        view.startOrderStatus(status);
    }

    @Override
    public void customerAccountError(String language) {
        String error = "";
        if (language.equals("spanish"))
            error = "ERROR:cliente_identificador_no_existe";
        else if (language.equals("english"))
            error = "ERROR:customer_identifier_does_not_exist";
        else if (language.equals("russian"))
            error = "ОШИБКА:клиент_идентификатор_делает_нет_существовать";

        view.startOrderStatus(error);
    }

    @Override
    public void droneIdentifierError(String language) {
        String error = "";
        if (language.equals("spanish"))
            error = "ERROR:dron_identificador_no_existe";
        else if (language.equals("english"))
            error = "ERROR:drone_identifier_does_not_exist";
        else if (language.equals("russian"))
            error = "ОШИБКА:дрон_идентификатор_делает_нет_существовать";

        view.startOrderStatus(error);
    }

    @Override
    public void orderError(String language) {
        String error = "";
        if (language.equals("spanish"))
            error = "ERROR:orden_identificador_ya_existe";
        else if (language.equals("english"))
            error = "ERROR:order_identifier_already_exists";
        else if (language.equals("russian"))
            error = "ОШИБКА:порядок_идентификатор_уже_существуют";

        view.startOrderStatus(error);
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

        view.startOrderStatus(error);
    }
}
