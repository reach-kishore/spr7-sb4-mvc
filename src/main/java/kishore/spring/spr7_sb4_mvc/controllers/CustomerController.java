package kishore.spring.spr7_sb4_mvc.controllers;

import kishore.spring.spr7_sb4_mvc.model.Customer;
import kishore.spring.spr7_sb4_mvc.services.CustomerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public List<Customer> listCustomers(){
        return customerService.listCustomers();
    }

    @GetMapping("/{customerId}")
    public Customer getCustomer(@PathVariable Integer customerId){
        return customerService.getCustomer(customerId);
    }

    @PostMapping
    public ResponseEntity addCustomer(@RequestBody Customer customer){
        customerService.addCustomer(customer);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.LOCATION, "/api/v1/customer/" + customer.getCustomerId().toString());
        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

}
