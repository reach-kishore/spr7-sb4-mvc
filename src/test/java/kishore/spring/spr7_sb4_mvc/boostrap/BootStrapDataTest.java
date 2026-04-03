package kishore.spring.spr7_sb4_mvc.boostrap;

import kishore.spring.spr7_sb4_mvc.bootstrap.BootStrapData;
import kishore.spring.spr7_sb4_mvc.repositories.BeerRepository;
import kishore.spring.spr7_sb4_mvc.repositories.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class BootStrapDataTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private BeerRepository beerRepository;

    BootStrapData bootStrapData;

    @BeforeEach
    public void setup() {
        bootStrapData = new BootStrapData(beerRepository, customerRepository);
    }

    @Test
    void run() throws Exception {
        bootStrapData.run();
        assertThat(beerRepository.count()).isEqualTo(2);
        assertThat(customerRepository.count()).isEqualTo(3);

    }
}
