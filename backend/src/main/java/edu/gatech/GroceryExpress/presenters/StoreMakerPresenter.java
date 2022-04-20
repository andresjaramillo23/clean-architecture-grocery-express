package edu.gatech.GroceryExpress.presenters;


import edu.gatech.GroceryExpress.interactors.responses.StoreMakerResponse;
import edu.gatech.GroceryExpress.presenters.views.StoreMakerView;
import edu.gatech.GroceryExpress.utility.GroceryExpressUtility;

public class StoreMakerPresenter implements StoreMakerResponse {
    private StoreMakerView view;

    public StoreMakerPresenter(StoreMakerView view) {
        this.view = view;
    }

    @Override
    public void storeIdentifierAlreadyExistError(String language) {
        String error = "";
        if (language.equals("spanish"))
            error = "ERROR:tienda_identificador_ya_existe";
        else if (language.equals("english"))
            error = "ERROR:store_identifier_already_exists";
        else if (language.equals("russian"))
            error = "ОШИБКА:хранить_идентификатор_уже_существуют";

        view.makeStoreStatus(error);
    }

    @Override
    public void success(String language) {
        String status = GroceryExpressUtility.getStatus(language);
        view.makeStoreStatus(status);
    }
}

