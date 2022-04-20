package edu.gatech.GroceryExpress.presenters;


import edu.gatech.GroceryExpress.interactors.responses.OrderDisplayerResponse;
import edu.gatech.GroceryExpress.models.Item;
import edu.gatech.GroceryExpress.models.Order;
import edu.gatech.GroceryExpress.presenters.views.OrderDisplayerView;

import java.util.List;

public class OrderDisplayerPresenter implements OrderDisplayerResponse {
    private OrderDisplayerView view;

    public OrderDisplayerPresenter(OrderDisplayerView view) {
        this.view = view;
    }

    @Override
    public void displayOrders(String language, String currency, List<Order> orders) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Order order : orders) {
            if (language.equals("english"))
                stringBuilder.append("orderID:").append(order.getOrder()).append(System.lineSeparator());
            else if (language.equals("spanish"))
                stringBuilder.append("ordenID:").append(order.getOrder()).append(System.lineSeparator());
            else if (language.equals("russian"))
                stringBuilder.append("порядокID:").append(order.getOrder()).append(System.lineSeparator());

            List<Item> items = order.getItems();
            if (items != null) {
                for (Item item : items) {
                    double itemPrice = getItemPrice(currency, item);

                    if (language.equals("english"))
                        stringBuilder.append("item_name:").append(item.getName()).append(",")
                            .append("total_quantity:").append(item.getQuantity()).append(",")
                            .append(String.format("total_cost:%.1f %s", itemPrice, currency)).append(",")
                            .append("total_weight:").append(item.getQuantity() * item.getWeight()).append(System.lineSeparator());
                    else if (language.equals("spanish"))
                        stringBuilder.append("articulo_nombre:").append(item.getName()).append(",")
                                .append("total_cantidad:").append(item.getQuantity()).append(",")
                                .append(String.format("total_costo:%.1f %s", itemPrice, currency)).append(",")
                                .append("total_peso:").append(item.getQuantity() * item.getWeight()).append(System.lineSeparator());
                    else if (language.equals("russian"))
                        stringBuilder.append("вещь_название:").append(item.getName()).append(",")
                                .append("общее_количество:").append(item.getQuantity()).append(",")
                                .append(String.format("общее_Стоимость:%.1f %s", itemPrice, currency)).append(",")
                                .append("общее_масса:").append(item.getQuantity() * item.getWeight()).append(System.lineSeparator());
                }
            }
        }

        if (language.equals("english"))
            stringBuilder.append("OK:display_completed");
        else if (language.equals("spanish"))
            stringBuilder.append("OK:muestra_completada");
        else if (language.equals("russian"))
            stringBuilder.append("OK:отображать_завершенный");

        view.displayOrders(stringBuilder.toString());
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

        view.displayOrders(error);
    }

    private double getItemPrice(String currency, Item item) {
        double revenue;
        if (item.getCurrency().equals("dollars") && currency.equals("euros"))
            revenue = item.getUnitPrice() * 0.92 * item.getQuantity();
        else if (item.getCurrency().equals("euros") && currency.equals("dollars"))
            revenue = item.getUnitPrice() * 1.08 * item.getQuantity();
        else
            revenue = item.getUnitPrice() * 1.0 * item.getQuantity();
        return revenue;
    }
}
