package kishore.spring.spr7_sb4_mvc.controllers;

import kishore.spring.spr7_sb4_mvc.entities.Beer;
import kishore.spring.spr7_sb4_mvc.exceptions.CustomNotFoundException;
import kishore.spring.spr7_sb4_mvc.model.BeerDTO;
import kishore.spring.spr7_sb4_mvc.services.BeerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@Slf4j
public class BeerController {

    public static final String BEER_PATH = "/api/v1/beer";
    public static final String BEER_PATH_ID = BEER_PATH+"/{beerId}";

    private final BeerService beerService;

    @PatchMapping(BEER_PATH_ID)
    public ResponseEntity updateBeerPatchById(@PathVariable("beerId") UUID beerId, @RequestBody BeerDTO beerDTO) {
        beerService.patchBeerById(beerId, beerDTO);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(BEER_PATH_ID)
    public ResponseEntity deleteBeerById(@PathVariable("beerId") UUID beerId) {
        if(!beerService.deleteById(beerId)) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        };
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping(BEER_PATH_ID)
    public ResponseEntity updateById(@PathVariable("beerId") UUID beerId, @RequestBody BeerDTO beerDTO) {
        if(beerService.updateBeerById(beerId, beerDTO).isEmpty()){
            throw new CustomNotFoundException();
        };
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping(BEER_PATH)
    public List<BeerDTO> getAllbeers(){
        log.debug("Get All Beers - Controller");
        return beerService.listBeers();
    }

    @GetMapping(BEER_PATH_ID)
    public BeerDTO getBeerById(@PathVariable("beerId") UUID beerId) {
        log.debug("Get Beer By ID - Controller");
        return beerService.getBeerbyId(beerId).orElseThrow(CustomNotFoundException::new);
    }

    @PostMapping(BEER_PATH)
    public ResponseEntity addBeer(@Validated @RequestBody BeerDTO beerDTO) {
        log.debug("Add Beer - Controller");
        BeerDTO beer = beerService.addBeer(beerDTO);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", BEER_PATH + "/" + beer.getId().toString());

        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    /**
     * This exception handler handles exc only generated from this controller.
     * Use ControllerAdvice to have this handled globally - meaning from all other controllers
     * @param e
     * @return
     */
    //@ExceptionHandler
    public ResponseEntity<String> handleException(Exception e) {
        log.debug("In the exception handler"+e.getMessage());
        return ResponseEntity.notFound().build();
        //return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

}
