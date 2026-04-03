package kishore.spring.spr7_sb4_mvc.mappers;

import kishore.spring.spr7_sb4_mvc.entities.Beer;
import kishore.spring.spr7_sb4_mvc.model.BeerDTO;
import org.mapstruct.Mapper;

@Mapper
public interface BeerMapper {

    Beer beerDtoToBeer(BeerDTO beerDTO);

    BeerDTO beerToBeerDto(Beer beer);
}
