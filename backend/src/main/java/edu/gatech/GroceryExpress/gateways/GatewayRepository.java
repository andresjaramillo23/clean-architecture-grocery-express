package edu.gatech.GroceryExpress.gateways;



import edu.gatech.GroceryExpress.models.Customer;
import edu.gatech.GroceryExpress.models.Pilot;
import edu.gatech.GroceryExpress.models.Store;

import java.util.List;

public interface GatewayRepository {

    void registerStore(Store store);

    List<Store> retrieveStores();

    void registerPilot(Pilot pilot);

    List<Pilot> retrievePilots();

    List<Customer> retrieveCustomers();

    void registerCustomer(Customer customer);
}
