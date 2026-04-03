package kishore.spring.spr7_sb4_mvc.repositories;

import kishore.spring.spr7_sb4_mvc.entities.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class CustomerRepositoryTest {

    @Autowired
    public CustomerRepository customerRepository;

    @Test
    void saveCustomer() {
        Customer customer = customerRepository.save(Customer.builder()
                .customerName("KK").build());

        assertThat(customer).isNotNull();
        assertThat(customer.getCustomerId()).isNotNull();
    }
}
