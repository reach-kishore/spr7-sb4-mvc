package kishore.spring.spr7_sb4_mvc.controllers;

import kishore.spring.spr7_sb4_mvc.exceptions.CustomNotFoundException;
import kishore.spring.spr7_sb4_mvc.model.Beer;
import kishore.spring.spr7_sb4_mvc.services.BeerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/api/v1/beer")
public class BeerController {

    private final BeerService beerService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Beer> getAllbeers(){
        log.debug("Get All Beers - Controller");
        return beerService.listBeers();
    }

    @RequestMapping(value = "/{beerId}", method =  RequestMethod.GET)
    public Beer getBeerById(@PathVariable("beerId") UUID beerId) {
        log.debug("Get Beer By ID - Controller");
        return beerService.getBeerbyId(beerId).orElseThrow(CustomNotFoundException::new);
    }

    @PostMapping
    public ResponseEntity addBeer(@RequestBody Beer beer) {
        log.debug("Add Beer - Controller");
        beerService.addBeer(beer);
        return new ResponseEntity(HttpStatus.CREATED);
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
