package kishore.spring.spr7_sb4_mvc.services;

import kishore.spring.spr7_sb4_mvc.model.Beer;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BeerService {

    List<Beer> listBeers();

    Optional<Beer> getBeerbyId(UUID id);

    void addBeer(Beer beer);
}
