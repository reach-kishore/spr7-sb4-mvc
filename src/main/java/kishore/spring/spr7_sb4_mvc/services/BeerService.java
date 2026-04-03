package kishore.spring.spr7_sb4_mvc.services;

import kishore.spring.spr7_sb4_mvc.entities.Beer;
import kishore.spring.spr7_sb4_mvc.model.BeerDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BeerService {

    List<BeerDTO> listBeers();

    Optional<BeerDTO> getBeerbyId(UUID id);

    BeerDTO addBeer(BeerDTO beerDTO);

    Optional<BeerDTO> updateBeerById(UUID beerId, BeerDTO beer);

    boolean deleteById(UUID beerId);

    void patchBeerById(UUID beerId, BeerDTO beer);
}
