package edu.gatech.GroceryExpress.gateways;

import edu.gatech.GroceryExpress.models.Customer;
import edu.gatech.GroceryExpress.models.Pilot;
import edu.gatech.GroceryExpress.models.Store;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class InMemoryGatewayRepository implements GatewayRepository {
    private static final Map<String, Store> stores = new HashMap<>();
    private static final Map<String, Pilot> pilots = new HashMap<>();
    private static final Map<String, Customer> customers = new HashMap<>();

    @Override
    public void registerStore(Store store) {
        stores.put(store.getStore(), store);
    }

    @Override
    public List<Store> retrieveStores() {
        return new ArrayList<>(new TreeMap<>(stores).values());
    }

    @Override
    public void registerPilot(Pilot pilot) {
        pilots.put(pilot.getAccount(), pilot);
    }

    @Override
    public List<Pilot> retrievePilots() {
        return new ArrayList<>(new TreeMap<>(pilots).values());
    }

    @Override
    public List<Customer> retrieveCustomers() {
        return new ArrayList<>(new TreeMap<>(customers).values());
    }

    @Override
    public void registerCustomer(Customer customer) {
        customers.put(customer.getAccount(), customer);
    }
}
