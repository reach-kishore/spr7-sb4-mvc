package kishore.spring.spr7_sb4_mvc.repositories;

import kishore.spring.spr7_sb4_mvc.entities.Beer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BeerRepository extends JpaRepository<Beer, UUID> {
}
