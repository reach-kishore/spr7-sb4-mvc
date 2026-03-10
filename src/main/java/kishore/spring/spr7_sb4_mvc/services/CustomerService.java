package kishore.spring.spr7_sb4_mvc.services;

import kishore.spring.spr7_sb4_mvc.model.Customer;

import java.util.List;
import java.util.Map;

public interface CustomerService {

    List<Customer> listCustomers();

    Customer getCustomer(int customerId);

    void addCustomer(Customer customer);
}
