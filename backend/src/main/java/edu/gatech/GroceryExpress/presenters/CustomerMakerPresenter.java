package edu.gatech.GroceryExpress.presenters;


import edu.gatech.GroceryExpress.interactors.responses.CustomerMakerResponse;
import edu.gatech.GroceryExpress.presenters.views.CustomerMakerView;
import edu.gatech.GroceryExpress.utility.GroceryExpressUtility;

public class CustomerMakerPresenter implements CustomerMakerResponse {
    private CustomerMakerView view;

    public CustomerMakerPresenter(CustomerMakerView view) {
        this.view = view;
    }

    @Override
    public void success(String language) {
        String status = GroceryExpressUtility.getStatus(language);
        view.makeCustomerStatus(status);
    }

    @Override
    public void customerAccountError(String language) {
        String error = "";
        if (language.equals("spanish"))
            error = "ERROR:cliente_identificador_ya_existe";
        else if (language.equals("english"))
            error = "ERROR:customer_identifier_already_exists";
        else if (language.equals("russian"))
            error = "ОШИБКА:клиент_идентификатор_уже_существуют";

        view.makeCustomerStatus(error);
    }
}
