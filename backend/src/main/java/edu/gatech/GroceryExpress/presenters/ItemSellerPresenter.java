package edu.gatech.GroceryExpress.presenters;


import edu.gatech.GroceryExpress.interactors.responses.ItemSellerResponse;
import edu.gatech.GroceryExpress.presenters.views.ItemSellerView;
import edu.gatech.GroceryExpress.utility.GroceryExpressUtility;

public class ItemSellerPresenter implements ItemSellerResponse {
    private ItemSellerView view;

    public ItemSellerPresenter(ItemSellerView view) {
        this.view = view;
    }

    @Override
    public void success(String language) {
        String status = GroceryExpressUtility.getStatus(language);
        view.sellItemStatus(status);
    }

    @Override
    public void itemError(String language) {
        String error = "";
        if (language.equals("spanish"))
            error = "ERROR:articulo_identificador_ya_existe";
        else if (language.equals("english"))
            error = "ERROR:item_identifier_already_exists";
        else if (language.equals("russian"))
            error = "ОШИБКА:вещь_идентификатор_уже_существуют";

        view.sellItemStatus(error);
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

        view.sellItemStatus(error);
    }
}
