package kishore.spring.spr7_sb4_mvc.controllers;

import kishore.spring.spr7_sb4_mvc.entities.Customer;
import kishore.spring.spr7_sb4_mvc.exceptions.CustomNotFoundException;
import kishore.spring.spr7_sb4_mvc.mappers.CustomerMapper;
import kishore.spring.spr7_sb4_mvc.model.CustomerDTO;
import kishore.spring.spr7_sb4_mvc.repositories.CustomerRepository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CustomerControllerIntegrationTest {

    @Autowired
    CustomerController customerController;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CustomerMapper customerMapper;

    @Test
    void testDeleteBeerByIdNotFound(){
        assertThat(customerController.deleteById(new Random().nextInt()).getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    @Rollback
    @Transactional
    void testDeleteBeerById(){
        Customer customer = customerRepository.findAll().getFirst();
        ResponseEntity responseEntity = customerController.deleteById(customer.getCustomerId());
        Assertions.assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        Assertions.assertThrows(CustomNotFoundException.class, () -> customerController.getCustomerById(customer.getCustomerId()));
    }

    @Test
    void testUpdateCustomerbyIdNotFound(){
        Assertions.assertThrows(CustomNotFoundException.class, () -> {
           customerController.updatebyId(new Random().nextInt(), CustomerDTO.builder().build());
        });
    }

    @Test
    void testUpdateCustomer(){
        Customer customer = customerRepository.findAll().getFirst();
        customer.setCustomerName("updated customer name");

        ResponseEntity responseEntity = customerController.updatebyId(customer.getCustomerId(), customerMapper.customerToCustomerDto(customer));
        assertThat(responseEntity).isNotNull();
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

        Customer c = customerRepository.findById(customer.getCustomerId()).get();
        assertThat(c.getCustomerName()).isEqualTo("updated customer name");
    }

    @Test
    void testAddCustomer() {
        CustomerDTO customerDTO = CustomerDTO.builder()
                .customerName("new customer")
                .build();
        ResponseEntity responseEntity = customerController.addCustomer(customerDTO);
        assertThat(responseEntity).isNotNull();
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(responseEntity.getHeaders().getLocation()).isNotNull();
    }

    @Test
    void testGetByIdNotFound(){
        assertThrows(CustomNotFoundException.class, () -> {
            customerController.getCustomerById(new Random().nextInt());
        });

    }

    @Test
    void testGetById() {
        Customer customer= customerRepository.findAll().getFirst();
        CustomerDTO customerDTO = customerController.getCustomerById(customer.getCustomerId());
        assertThat(customerDTO).isNotNull();
        assertThat( customerDTO.getCustomerId()).isEqualTo(customer.getCustomerId());

    }

    @Test
    void tesetListAll(){
        List<CustomerDTO> customerDtos = customerController.listCustomers();
        assertThat(customerDtos.size()).isGreaterThan(0);
    }

    @Test
    @Rollback
    @Transactional
    void testListAllEmptyList(){
        customerRepository.deleteAll();
        assertThat(customerController.listCustomers()).isEmpty();
    }

}