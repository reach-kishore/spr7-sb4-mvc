package kishore.spring.spr7_sb4_mvc.repositories;

import kishore.spring.spr7_sb4_mvc.model.Beer;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface BeerRepository extends CrudRepository<Beer, UUID> {
}
