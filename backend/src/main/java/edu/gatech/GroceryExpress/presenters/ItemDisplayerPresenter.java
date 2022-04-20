package edu.gatech.GroceryExpress.presenters;


import edu.gatech.GroceryExpress.interactors.responses.ItemDisplayerResponse;
import edu.gatech.GroceryExpress.models.Item;
import edu.gatech.GroceryExpress.presenters.views.ItemDisplayerView;

import java.util.List;

public class ItemDisplayerPresenter implements ItemDisplayerResponse {
    private ItemDisplayerView view;

    public ItemDisplayerPresenter(ItemDisplayerView view) {
        this.view = view;
    }

    @Override
    public void displayItems(String language, List<Item> items) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Item item : items)
            stringBuilder.append(item.getName()).append(",").append(item.getWeight()).append(System.lineSeparator());

        if (language.equals("english"))
            stringBuilder.append("OK:display_completed");
        else if (language.equals("spanish"))
            stringBuilder.append("OK:muestra_completada");
        else if (language.equals("russian"))
            stringBuilder.append("OK:отображать_завершенный");

        view.displayItems(stringBuilder.toString());

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

        view.displayItems(error);
    }
}

