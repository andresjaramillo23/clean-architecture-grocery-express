package edu.gatech.GroceryExpress.interactors;

import edu.gatech.GroceryExpress.GroceryExpress;
import edu.gatech.GroceryExpress.gateways.GatewayFactory;
import edu.gatech.GroceryExpress.gateways.GatewayRepository;
import edu.gatech.GroceryExpress.gateways.InMemoryGatewayRepository;
import edu.gatech.GroceryExpress.interactors.responses.ItemSellerResponse;
import edu.gatech.GroceryExpress.models.Item;
import edu.gatech.GroceryExpress.models.Store;
import edu.gatech.GroceryExpress.services.requests.ItemSellerRequest;
import edu.gatech.GroceryExpress.utility.GroceryExpressUtility;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ItemSeller implements ItemSellerRequest {
    private ItemSellerResponse response;
    private String store;
    private String item;
    private String weight;
    private String language;
    private InMemoryGatewayRepository gatewayFactory;

    public ItemSeller(ItemSellerResponse response, InMemoryGatewayRepository gatewayFactory) {
        this.response = Objects.requireNonNull(response);
        this.gatewayFactory = gatewayFactory;
    }

    @Override
    public void execute() {
        GatewayRepository gatewayRepository = gatewayFactory;
        List<Store> stores = new ArrayList<>(gatewayRepository.retrieveStores());

        List<Item> items = stores.stream()
                .filter(x -> x.getStore().equals(store))
                .flatMap(y -> y.getItems().stream())
                .collect(Collectors.toList());

        if (GroceryExpressUtility.isMissing(stores, store))
            response.storeError(language);
        else if (hasDuplicateItem(items))
            response.itemError(language);
        else {
            response.success(language);
            storeItemToStore(stores);
            gatewayRepository.registerStore(GroceryExpressUtility.getStore(stores, store));
        }
    }

    @Override
    public void setLanguage(String language) {
        this.language = language;
    }

    private void storeItemToStore(List<Store> stores) {
        Store store = GroceryExpressUtility.getStore(stores, this.store);
        if (store != null)
            store.addItems(new Item(item, Integer.parseInt(weight), ""));
    }

    private boolean hasDuplicateItem(List<Item> items) {
        return items.stream().anyMatch(x -> x.getName().equals(item));
    }

    @Override
    public void setStore(String storeName) {
        this.store = storeName;
    }

    @Override
    public void setItem(String item) {
        this.item = item;
    }

    @Override
    public void setWeight(String price) {
        this.weight = price;
    }
}
