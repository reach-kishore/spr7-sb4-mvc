package kishore.spring.spr7_sb4_mvc.bootstrap;

import kishore.spring.spr7_sb4_mvc.model.Beer;
import kishore.spring.spr7_sb4_mvc.model.BeerStyle;
import kishore.spring.spr7_sb4_mvc.repositories.BeerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class BootStrapData implements CommandLineRunner {

    private final BeerRepository beerRepository;

    public BootStrapData(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Beer beer1 = Beer.builder()
                .id(UUID.randomUUID())
                .version(1)
                .beerName("Corona Extra from Bootstrap")
                .beerStyle(BeerStyle.LAGER)
                .upc("123")
                .price(new BigDecimal("4.00"))
                .quantity(100)
                .createdDt(LocalDateTime.now())
                .updateDt(LocalDateTime.now())
                .build();
        Beer beer2 = Beer.builder()
                .id(UUID.randomUUID())
                .version(1)
                .beerName("Hoegaarden from Bootstrap")
                .beerStyle(BeerStyle.WHEAT_BEER)
                .upc("345")
                .price(new BigDecimal("4.00"))
                .quantity(100)
                .createdDt(LocalDateTime.now())
                .updateDt(LocalDateTime.now())
                .build();

        Beer beer1Saved = beerRepository.save(beer1);
        Beer beer2Saved = beerRepository.save(beer2);

        System.out.println("No of beers saved in bootstrap: " + beerRepository.count());
    }
}
