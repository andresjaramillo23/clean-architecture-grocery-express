package edu.gatech.GroceryExpress.presenters;


import edu.gatech.GroceryExpress.interactors.responses.StoreDisplayerResponse;
import edu.gatech.GroceryExpress.models.Store;
import edu.gatech.GroceryExpress.presenters.models.DeliveryServiceViewModel;
import edu.gatech.GroceryExpress.presenters.views.StoreDisplayerView;

import java.util.List;

public class StoreDisplayerPresenter implements StoreDisplayerResponse {
    private StoreDisplayerView view;

    public StoreDisplayerPresenter(StoreDisplayerView view) {
        this.view = view;
    }

    @Override
    public void displayStores(String language, String currency, List<Store> stores) {
        StringBuilder stringBuilder = new StringBuilder();
        double revenue;

        for (Store store : stores) {
            revenue = getRevenue(currency, store);

            if (language.equals("english"))
                stringBuilder.append(String.format("name:%s,revenue:%.1f %s", store.getStore(), revenue, currency)).append(System.lineSeparator());
            else if (language.equals("spanish"))
                stringBuilder.append(String.format("nombre:%s,ingresos:%.1f %s", store.getStore(), revenue, currency)).append(System.lineSeparator());
            else if (language.equals("russian"))
                stringBuilder.append(String.format("название:%s,доход:%.1f %s", store.getStore(), revenue, currency)).append(System.lineSeparator());
        }

        if (language.equals("english"))
            stringBuilder.append("OK:display_completed");
        else if (language.equals("spanish"))
            stringBuilder.append("OK:muestra_completada");
        else if (language.equals("russian"))
            stringBuilder.append("OK:отображать_завершенный");

        DeliveryServiceViewModel model = new DeliveryServiceViewModel();
        model.stores = stores;
        model.displayStores = stringBuilder.toString();

        view.displayStores(model);
    }

    private double getRevenue(String currency, Store store) {
        double revenue;
        if (store.getCurrency().equals("dollars") && currency.equals("euros"))
            revenue = store.getRevenue() * 0.92;
        else if (store.getCurrency().equals("euros") && currency.equals("dollars"))
            revenue = store.getRevenue() * 1.08;
        else
            revenue = store.getRevenue() * 1.0;
        return revenue;
    }
}

