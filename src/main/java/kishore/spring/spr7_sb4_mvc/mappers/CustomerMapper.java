package kishore.spring.spr7_sb4_mvc.mappers;

import kishore.spring.spr7_sb4_mvc.entities.Customer;
import kishore.spring.spr7_sb4_mvc.model.CustomerDTO;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper {

    Customer customerDtoToCustomer(CustomerDTO customerDTO);

    CustomerDTO customerToCustomerDto(Customer customer);

}
