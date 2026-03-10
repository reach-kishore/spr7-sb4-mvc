package kishore.spring.spr7_sb4_mvc.services.impl;

import kishore.spring.spr7_sb4_mvc.model.Customer;
import kishore.spring.spr7_sb4_mvc.services.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private Map<Integer, Customer> customers;

    public CustomerServiceImpl() {
        customers = new HashMap();
        Customer customer1 = Customer.builder()
                .customerId(1)
                .customerName("Cust 111")
                .version("1")
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();
        Customer customer2 = Customer.builder()
                .customerId(2)
                .customerName("Cust 2")
                .version("1")
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();
        Customer customer3 = Customer.builder()
                .customerId(3)
                .customerName("Cust 3")
                .version("1")
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();
        customers.put(customer1.getCustomerId(), customer1);
        customers.put(customer2.getCustomerId(), customer2);
        customers.put(customer3.getCustomerId(), customer3);
    }

    @Override
    public List<Customer> listCustomers() {
        return new ArrayList<>(customers.values());
    }

    @Override
    public Customer getCustomer(int customerId) {
        return customers.get(customerId);
    }

    @Override
    public void addCustomer(Customer customer) {
        Customer newCustomer = Customer.builder()
                .customerId(customer.getCustomerId())
                .customerName(customer.getCustomerName())
                .version(customer.getVersion())
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();
        customers.put(customer.getCustomerId(), newCustomer);
    }
}
