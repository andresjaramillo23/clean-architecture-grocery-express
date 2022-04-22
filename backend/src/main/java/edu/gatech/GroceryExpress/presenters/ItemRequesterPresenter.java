package edu.gatech.GroceryExpress.presenters;


import edu.gatech.GroceryExpress.interactors.responses.ItemRequesterResponse;
import edu.gatech.GroceryExpress.presenters.views.ItemRequesterView;
import edu.gatech.GroceryExpress.utility.GroceryExpressUtility;

public class ItemRequesterPresenter implements ItemRequesterResponse {
    private ItemRequesterView view;

    public ItemRequesterPresenter(ItemRequesterView view) {
        this.view = view;
    }

    @Override
    public void success(String language) {
        String status = GroceryExpressUtility.getStatus(language);
        view.requestItemStatus(status);
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

        view.requestItemStatus(error);
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

        view.requestItemStatus(error);
    }

    @Override
    public void missingItemError(String language) {
        String error = "";
        if (language.equals("spanish"))
            error = "ERROR:articulo_identificador_no_existe";
        else if (language.equals("english"))
            error = "ERROR:item_identifier_does_not_exist";
        else if (language.equals("russian"))
            error = "ОШИБКА:вещь_идентификатор_делает_нет_существовать";

        view.requestItemStatus(error);
    }

    @Override
    public void itemDuplicateError(String language) {
        String error = "";
        if (language.equals("spanish"))
            error = "ERROR:articulo_fue_ordenado";
        else if (language.equals("english"))
            error = "ERROR:item_already_ordered";
        else if (language.equals("russian"))
            error = "ОШИБКА:вещь_вещь_приказал";

        view.requestItemStatus(error);
    }

    @Override
    public void noFundsCustomerError(String language) {
        String error = "";
        if (language.equals("spanish"))
            error = "ERROR:cliente_no_puede_pagar_nuevo_articulo";
        else if (language.equals("english"))
            error = "ERROR:customer_cant_afford_new_item";
        else if (language.equals("russian"))
            error = "ОШИБКА:клиент_не_может_позволить_себе_новый_товар";

        view.requestItemStatus(error);
    }

    @Override
    public void droneCapacityError(String language) {
        String error = "";
        if (language.equals("spanish"))
            error = "ERROR:dron_no_puede_cargar_nuevo_articulo";
        else if (language.equals("english"))
            error = "ERROR:drone_cant_carry_new_item";
        else if (language.equals("russian"))
            error = "ОШИБКА:дрон_не_мочь_нести_новый_вещь";

        view.requestItemStatus(error);
    }
}
