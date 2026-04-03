package kishore.spring.spr7_sb4_mvc.repositories;

import kishore.spring.spr7_sb4_mvc.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
