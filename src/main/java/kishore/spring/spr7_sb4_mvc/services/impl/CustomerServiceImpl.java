package kishore.spring.spr7_sb4_mvc.services.impl;

import kishore.spring.spr7_sb4_mvc.model.CustomerDTO;
import kishore.spring.spr7_sb4_mvc.services.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private Map<Integer, CustomerDTO> customers;

    public CustomerServiceImpl() {
        customers = new HashMap();
        CustomerDTO customerDTO1 = CustomerDTO.builder()
                .customerId(1)
                .customerName("Cust 111")
                .version("1")
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();
        CustomerDTO customerDTO2 = CustomerDTO.builder()
                .customerId(2)
                .customerName("Cust 2")
                .version("1")
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();
        CustomerDTO customerDTO3 = CustomerDTO.builder()
                .customerId(3)
                .customerName("Cust 3")
                .version("1")
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();
        customers.put(customerDTO1.getCustomerId(), customerDTO1);
        customers.put(customerDTO2.getCustomerId(), customerDTO2);
        customers.put(customerDTO3.getCustomerId(), customerDTO3);
    }

    @Override
    public List<CustomerDTO> listCustomers() {
        return new ArrayList<>(customers.values());
    }

    @Override
    public Optional<CustomerDTO> getCustomer(int customerId) {
        return Optional.ofNullable(customers.get(customerId));
    }

    @Override
    public CustomerDTO addCustomer(CustomerDTO customerDTO) {
        CustomerDTO newCustomerDTO = CustomerDTO.builder()
                .customerId(customerDTO.getCustomerId())
                .customerName(customerDTO.getCustomerName())
                .version(customerDTO.getVersion())
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();
        customers.put(customerDTO.getCustomerId(), newCustomerDTO);
        return customerDTO;
    }

    @Override
    public Optional<CustomerDTO> updateCustomerById(int customerId, CustomerDTO customer) {
        return null;
    }

    @Override
    public boolean deleteCustomerById(int customerId) {
        return false;
    }

    @Override
    public void patchCustomerById(int customerId, CustomerDTO customer) {

    }
}
