package kishore.spring.spr7_sb4_mvc.services.impl;

import kishore.spring.spr7_sb4_mvc.mappers.CustomerMapper;
import kishore.spring.spr7_sb4_mvc.model.CustomerDTO;
import kishore.spring.spr7_sb4_mvc.repositories.CustomerRepository;
import kishore.spring.spr7_sb4_mvc.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
@Primary
@RequiredArgsConstructor
public class CustomerServiceJPA implements CustomerService {

  private final CustomerRepository customerRepository;
  private final CustomerMapper customerMapper;

  @Override
  public List<CustomerDTO> listCustomers() {
    return customerRepository.findAll().stream()
        .map(customerMapper::customerToCustomerDto)
        .collect(Collectors.toList());
  }

  @Override
  public Optional<CustomerDTO> getCustomer(int customerId) {
    return Optional.ofNullable(
        customerMapper.customerToCustomerDto(customerRepository.findById(customerId).orElse(null)));
  }

  @Override
  public CustomerDTO addCustomer(CustomerDTO customerDTO) {
    return customerMapper.customerToCustomerDto(
        customerRepository.save(customerMapper.customerDtoToCustomer(customerDTO)));
  }

  @Override
  public Optional<CustomerDTO> updateCustomerById(int customerId, CustomerDTO customer) {
      AtomicReference<Optional<CustomerDTO>> atomicReference = new AtomicReference<>();
      customerRepository.findById(customerId).ifPresentOrElse(existingCustomer -> {
         existingCustomer.setCustomerName(customer.getCustomerName());
         existingCustomer.setLastModifiedDate(customer.getLastModifiedDate());
          atomicReference.set(Optional.ofNullable(customerMapper.customerToCustomerDto(customerRepository.save(existingCustomer))));
      },  () -> atomicReference.set(Optional.empty()) );

      return atomicReference.get();
  }

  @Override
  public boolean deleteCustomerById(int customerId) {
      if(customerRepository.findById(customerId).isPresent()) {
          customerRepository.deleteById(customerId);
          return true;
      }
      return false;
  }

  @Override
  public void patchCustomerById(int customerId, CustomerDTO customer) {}
}
