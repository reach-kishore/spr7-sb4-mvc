package kishore.spring.spr7_sb4_mvc.bootstrap;

import kishore.spring.spr7_sb4_mvc.entities.Beer;
import kishore.spring.spr7_sb4_mvc.entities.Customer;
import kishore.spring.spr7_sb4_mvc.model.BeerStyle;
import kishore.spring.spr7_sb4_mvc.repositories.BeerRepository;
import kishore.spring.spr7_sb4_mvc.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.UUID;

@Component
public class BootStrapData implements CommandLineRunner {

    private final BeerRepository beerRepository;
    private final CustomerRepository customerRepository;

    public BootStrapData(BeerRepository beerRepository, CustomerRepository customerRepository) {
        this.beerRepository = beerRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadBeerData();
        loadCustomerData();
    }

    private void loadCustomerData() {
        Customer customer1 = Customer.builder()
                .customerName("Cust 111")
                //.version(1)
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();
        Customer customer2 = Customer.builder()
                .customerName("Cust 2")
                //.version(1)
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();
        Customer customer3 = Customer.builder()
                .customerName("Cust 3")
                //.version(1)
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();

        customerRepository.saveAll(Arrays.asList(customer1, customer2, customer3));
        System.out.println("No of Customers saved in bootstrap: " + customerRepository.count());
    }

    private void loadBeerData() {
        Beer beer1 = Beer.builder()
//                .id(UUID.randomUUID())
//                .version(1)
                .beerName("Corona Extra from Bootstrap")
                .beerStyle(BeerStyle.LAGER)
                .upc("123")
                .price(new BigDecimal("4.00"))
                .quantity(100)
                .createdDt(LocalDateTime.now())
                .updateDt(LocalDateTime.now())
                .build();
        Beer beer2 = Beer.builder()
//                .id(UUID.randomUUID())
//                .version(1)
                .beerName("Hoegaarden from Bootstrap")
                .beerStyle(BeerStyle.WHEAT_BEER)
                .upc("345")
                .price(new BigDecimal("4.00"))
                .quantity(100)
                .createdDt(LocalDateTime.now())
                .updateDt(LocalDateTime.now())
                .build();

        beerRepository.save(beer1);
        beerRepository.save(beer2);

        System.out.println("No of beers saved in bootstrap: " + beerRepository.count());
    }
}
