package kishore.spring.spr7_sb4_mvc.controllers;

import kishore.spring.spr7_sb4_mvc.exceptions.CustomNotFoundException;
import kishore.spring.spr7_sb4_mvc.model.CustomerDTO;
import kishore.spring.spr7_sb4_mvc.services.CustomerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@AllArgsConstructor
//@RequestMapping("/api/v1/customer")
public class CustomerController {

    public static final String CUST_PATH = "/api/v1/customer";
    public static final String CUST_PATH_ID = CUST_PATH+"/{customerId}";

    private final CustomerService customerService;

    @PatchMapping(CUST_PATH_ID)
    public ResponseEntity updateCustomerByPatchId(@PathVariable("customerId") int customerId, @RequestBody CustomerDTO customerDTO) {
        customerService.patchCustomerById(customerId, customerDTO);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping(CUST_PATH_ID)
    public ResponseEntity deleteById(@PathVariable("customerId") int customerId) {
        if(customerService.deleteCustomerById(customerId)){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PutMapping(CUST_PATH_ID)
    public ResponseEntity updatebyId(@PathVariable("customerId") int customerId, @RequestBody CustomerDTO customerDTO) {
        if(customerService.updateCustomerById(customerId, customerDTO).isEmpty()){
            throw new CustomNotFoundException();
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping(CUST_PATH)
    public List<CustomerDTO> listCustomers(){
        return customerService.listCustomers();
    }

    @GetMapping(CUST_PATH_ID)
    public CustomerDTO getCustomerById(@PathVariable Integer customerId){
        return customerService.getCustomer(customerId).orElseThrow(CustomNotFoundException::new);
    }

    @PostMapping(CUST_PATH_ID)
    public ResponseEntity addCustomer(@RequestBody CustomerDTO customerDTO){
        CustomerDTO addedCustomer = customerService.addCustomer(customerDTO);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.LOCATION, "/api/v1/customer/" + addedCustomer.getCustomerId().toString());
        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

}
