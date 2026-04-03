package kishore.spring.spr7_sb4_mvc.services.impl;

import kishore.spring.spr7_sb4_mvc.entities.Beer;
import kishore.spring.spr7_sb4_mvc.model.BeerDTO;
import kishore.spring.spr7_sb4_mvc.model.BeerStyle;
import kishore.spring.spr7_sb4_mvc.services.BeerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Service
@Slf4j
@AllArgsConstructor
public class BeerServiceImpl implements BeerService {

//    private final BeerRepository beerRepository;
    private Map<UUID, BeerDTO> beerMap;

//    public  BeerServiceImpl(BeerRepository beerRepository) {this.beerRepository = beerRepository;}

    public BeerServiceImpl(){
        this.beerMap = new HashMap<>();
        BeerDTO beer1 = BeerDTO.builder()
                .id(UUID.randomUUID())
                .version(1)
                .beerName("Corona Extra")
                .beerStyle(BeerStyle.LAGER)
                .upc("123")
                .price(new BigDecimal("4.00"))
                .quantity(100)
                .createdDt(LocalDateTime.now())
                .updateDt(LocalDateTime.now())
                .build();
        BeerDTO beer2 = BeerDTO.builder()
                .id(UUID.randomUUID())
                .version(1)
                .beerName("Hoegaarden")
                .beerStyle(BeerStyle.WHEAT_BEER)
                .upc("345")
                .price(new BigDecimal("4.00"))
                .quantity(100)
                .createdDt(LocalDateTime.now())
                .updateDt(LocalDateTime.now())
                .build();

        beerMap.put(beer1.getId(), beer1);
        beerMap.put(beer2.getId(), beer2);
    }

    @Override
    public List<BeerDTO> listBeers(){
        return new ArrayList<>(beerMap.values());
//        return List.of(beerRepository.findAll());
        //return new ArrayList<>((Collection) beerRepository.findAll());
    }

    @Override
    public Optional<BeerDTO> getBeerbyId(UUID id) {
        log.debug("Get Beer ID Service called");
        return Optional.ofNullable(beerMap.get(id));
//        return beerRepository.findById(id);
    }

//    @Override
//    public void addBeer(Beer beer) {
//        Beer newBeer = Beer.builder()
//                .id(UUID.randomUUID())
//                .upc(beer.getUpc())
//                .price(beer.getPrice())
//                .beerStyle(beer.getBeerStyle())
//                .version(beer.getVersion())
//                .createdDt(LocalDateTime.now())
//                .updateDt(LocalDateTime.now())
//                .quantity(beer.getQuantity())
//                .beerName(beer.getBeerName())
//                .build();
//        beerMap.put(newBeer.getId(), newBeer);
//    }

    @Override
    public BeerDTO addBeer(BeerDTO beerDTO) {
        BeerDTO newBeerDTO = BeerDTO.builder()
                .id(UUID.randomUUID())
                .upc(beerDTO.getUpc())
                .price(beerDTO.getPrice())
                .beerStyle(beerDTO.getBeerStyle())
                .version(beerDTO.getVersion())
                .createdDt(LocalDateTime.now())
                .updateDt(LocalDateTime.now())
                .quantity(beerDTO.getQuantity())
                .beerName(beerDTO.getBeerName())
                .build();
        beerMap.put(newBeerDTO.getId(), newBeerDTO);
        beerMap.put(newBeerDTO.getId(), newBeerDTO);
        return  newBeerDTO;
    }

    @Override
    public Optional<BeerDTO> updateBeerById(UUID beerId, BeerDTO beer) {
        return null;
    }

    @Override
    public boolean deleteById(UUID beerId) {
        return false;
    }

    @Override
    public void patchBeerById(UUID beerId, BeerDTO beer) {

    }
}
