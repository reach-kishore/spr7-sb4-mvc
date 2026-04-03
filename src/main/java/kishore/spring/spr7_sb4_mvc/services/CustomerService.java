package kishore.spring.spr7_sb4_mvc.services;

import kishore.spring.spr7_sb4_mvc.model.CustomerDTO;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    List<CustomerDTO> listCustomers();

    Optional<CustomerDTO> getCustomer(int customerId);

    CustomerDTO addCustomer(CustomerDTO customerDTO);

    Optional<CustomerDTO> updateCustomerById(int customerId, CustomerDTO customer);

    boolean deleteCustomerById(int customerId);

    void patchCustomerById(int customerId, CustomerDTO customer);
}
