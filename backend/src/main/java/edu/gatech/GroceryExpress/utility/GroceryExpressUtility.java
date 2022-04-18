package edu.gatech.GroceryExpress.utility;

import edu.gatech.GroceryExpress.models.Drone;
import edu.gatech.GroceryExpress.models.Item;
import edu.gatech.GroceryExpress.models.Order;
import edu.gatech.GroceryExpress.models.Store;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class GroceryExpressUtility {

    public static boolean isMissing(List<Store> stores, String match) {
        return stores.stream().noneMatch(x -> x.getStore().equals(match));
    }

    public static Store getStore(List<Store> stores, String store) {
        return stores.stream().filter(x -> x.getStore().equals(store)).findFirst().orElse(null);
    }

    public static List<Drone> getDrones(List<Store> stores, String store) {
        return stores.stream().filter(x -> x.getStore().equals(store)).flatMap(x -> x.getDrones().stream())
                .sorted(Comparator.comparing(Drone::getDrone))
                .collect(Collectors.toList());
    }

    public static List<Order> getOrders(List<Store> stores, String store) {
        return stores.stream().filter(x -> x.getStore().equals(store)).flatMap(x -> x.getOrders().stream())
                .sorted(Comparator.comparing(Order::getOrder))
                .collect(Collectors.toList());
    }

    public static List<Item> getItems(List<Store> stores, String store) {
        return stores.stream().filter(x -> x.getStore().equals(store)).flatMap(x -> x.getItems().stream())
                .sorted(Comparator.comparing(Item::getName))
                .collect(Collectors.toList());
    }

    public static String getStatus(String language) {
        String status = "";
        if (language.equals("spanish"))
            status = "OK:cambio_completado";
        else if (language.equals("english"))
            status = "OK:change_completed";
        else if (language.equals("russian"))
            status = "OK:сдача_завершенный";
        return status;
    }
}
