package kishore.spring.spr7_sb4_mvc.repositories;

import kishore.spring.spr7_sb4_mvc.entities.Beer;
import kishore.spring.spr7_sb4_mvc.model.BeerDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class BeerRepositoryTest {

    @Autowired
    private BeerRepository beerRepository;

    @Test
    void testSaveBeer(){
        Beer beer = Beer.builder().beerName("Corona").build();
        beerRepository.save(beer);

        assertThat(beer).isNotNull();
        assertThat(beer.getId()).isNotNull();
    }
}
