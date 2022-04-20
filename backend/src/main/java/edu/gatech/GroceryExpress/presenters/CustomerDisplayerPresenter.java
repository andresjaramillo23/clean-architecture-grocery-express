package edu.gatech.GroceryExpress.presenters;


import edu.gatech.GroceryExpress.interactors.responses.CustomerDisplayerResponse;
import edu.gatech.GroceryExpress.models.Customer;
import edu.gatech.GroceryExpress.presenters.models.DeliveryServiceViewModel;
import edu.gatech.GroceryExpress.presenters.views.CustomerDisplayerView;

import java.util.List;

public class CustomerDisplayerPresenter implements CustomerDisplayerResponse {
    private CustomerDisplayerView view;

    public CustomerDisplayerPresenter(CustomerDisplayerView view) {
        this.view = view;
    }

    @Override
    public void displayCustomers(String language, String currency, List<Customer> customers) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Customer customer : customers) {
            double credit = getCredit(currency, customer);

            if (language.equals("english"))
                (stringBuilder.append("name:").append(customer.getFirstName()).append("_").append(customer.getLastName()).append(",")
                        .append("phone:").append(customer.getPhoneNumber()).append(",")
                        .append("rating:")).append(customer.getRating()).append(",")
                        .append(String.format("credit:%.1f %s", credit, currency)).append(System.lineSeparator());
            else if (language.equals("spanish"))
                (stringBuilder.append("nombre:").append(customer.getFirstName()).append("_").append(customer.getLastName()).append(",")
                        .append("telefono:").append(customer.getPhoneNumber()).append(",")
                        .append("clasificación:")).append(customer.getRating()).append(",")
                        .append(String.format("credito:%.1f %s", credit, currency)).append(System.lineSeparator());
            else if (language.equals("russian"))
                (stringBuilder.append("название:").append(customer.getFirstName()).append("_").append(customer.getLastName()).append(",")
                        .append("Телефон:").append(customer.getPhoneNumber()).append(",")
                        .append("рейтинг:")).append(customer.getRating()).append(",")
                        .append(String.format("кредит:%.1f %s", credit, currency)).append(System.lineSeparator());
        }

        if (language.equals("english"))
            stringBuilder.append("OK:display_completed");
        else if (language.equals("spanish"))
            stringBuilder.append("OK:muestra_completada");
        else if (language.equals("russian"))
            stringBuilder.append("OK:отображать_завершенный");

        DeliveryServiceViewModel model = new DeliveryServiceViewModel();
        model.customers = customers;
        model.displayCustomers = stringBuilder.toString();

        view.displayCustomers(model);



    }

    private double getCredit(String currency, Customer customer) {
        double revenue;
        if (customer.getCurrency().equals("dollars") && currency.equals("euros"))
            revenue = customer.getCredit() * 0.92;
        else if (customer.getCurrency().equals("euros") && currency.equals("dollars"))
            revenue = customer.getCredit() * 1.08;
        else
            revenue = customer.getCredit() * 1.0;
        return revenue;
    }
}
