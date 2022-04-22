package edu.gatech.GroceryExpress.presenters;


import edu.gatech.GroceryExpress.interactors.responses.OrderCancellerResponse;
import edu.gatech.GroceryExpress.presenters.views.OrderCancellerView;
import edu.gatech.GroceryExpress.utility.GroceryExpressUtility;

public class OrderCancellerPresenter implements OrderCancellerResponse {
    private OrderCancellerView view;

    public OrderCancellerPresenter(OrderCancellerView view) {
        this.view = view;
    }

    @Override
    public void success(String language) {
        String status = GroceryExpressUtility.getStatus(language);
        view.cancelOrderStatus(status);
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

        view.cancelOrderStatus(error);
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

        view.cancelOrderStatus(error);
    }
}
