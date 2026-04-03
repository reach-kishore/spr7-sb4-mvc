package kishore.spring.spr7_sb4_mvc.controllers;

import kishore.spring.spr7_sb4_mvc.entities.Beer;
import kishore.spring.spr7_sb4_mvc.exceptions.CustomNotFoundException;
import kishore.spring.spr7_sb4_mvc.mappers.BeerMapper;
import kishore.spring.spr7_sb4_mvc.model.BeerDTO;
import kishore.spring.spr7_sb4_mvc.repositories.BeerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class BeerControllerIntegrationTest {

    @Autowired
    BeerController controller;

    @Autowired
    BeerRepository beerRepository;

    @Autowired
    BeerMapper beerMapper;

    @Test
    @Rollback
    @Transactional
    void testDeleteBeerByIdNotFound(){
       ResponseEntity responseEntity = controller.deleteBeerById(UUID.randomUUID());
       assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    @Rollback
    @Transactional
    void testDeleteBeerById(){
        Beer deletedBeer = beerRepository.findAll().getFirst();
        ResponseEntity responseEntity = controller.deleteBeerById(deletedBeer.getId());
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        Assertions.assertThrows(CustomNotFoundException.class, () -> controller.getBeerById(deletedBeer.getId()));

        responseEntity = controller.deleteBeerById(deletedBeer.getId());
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void testUpdateBeerByIdNotFound(){
        Assertions.assertThrows(CustomNotFoundException.class, () -> {
            controller.updateById(UUID.randomUUID(), BeerDTO.builder().build());
        });
    }

    @Test
    @Rollback
    @Transactional
    void testUpdateBeerByID(){
        Beer beer = beerRepository.findAll().getFirst();
        BeerDTO updateBeerDTO = beerMapper.beerToBeerDto(beer);
        String updatedBeerName = "UPDATED BEER";
        updateBeerDTO.setBeerName(updatedBeerName);
        updateBeerDTO.setId(null);
        updateBeerDTO.setVersion(null);

        ResponseEntity responseEntity = controller.updateById(beer.getId(), updateBeerDTO);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

        Beer udpatedBeer = beerRepository.findById(beer.getId()).get();
        assertThat(udpatedBeer.getBeerName()).isEqualTo(updatedBeerName);

    }

    @Test
    @Rollback
    @Transactional
    void testAddBeer() {
        BeerDTO beerDTO = BeerDTO.builder()
                .beerName("test add beer")
                .build();
        ResponseEntity responseEntity = controller.addBeer(beerDTO);
        assertThat(responseEntity.getStatusCode()).isNotNull();
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(HttpStatus.CREATED.value()));
        assertThat(responseEntity.getHeaders().getLocation()).isNotNull();

        String[] locationUUID = responseEntity.getHeaders().getLocation().getPath().split("/");
        UUID beerId = UUID.fromString(locationUUID[4]);
        Beer beer = beerRepository.findById(beerId).get();
        assertThat(beer).isNotNull();

    }

    @Test
    void testListAllBeers() {
        List<BeerDTO> beerDTOList = controller.getAllbeers();
        assertThat(beerDTOList.size()).isEqualTo(2);
    }

    @Test
    @Transactional
    void testEmptyList(){
        beerRepository.deleteAll();
        assertThat(controller.getAllbeers().size()).isEqualTo(0);
    }

    @Test
    void testGetBeerById() {
        Beer beer = beerRepository.findAll().get(0);
        assertThat(controller.getBeerById(beer.getId())).isNotNull();
    }

    @Test
    void testGetBeerByIdNotFound() {
        Assertions.assertThrows(CustomNotFoundException.class,
                () -> controller.getBeerById(UUID.randomUUID()));
    }
}
