package kishore.spring.spr7_sb4_mvc.controllers;

import kishore.spring.spr7_sb4_mvc.model.BeerDTO;
import kishore.spring.spr7_sb4_mvc.services.BeerService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@SpringBootTest
class BeerControllerTest {

    @Autowired
    BeerController beerController;

    @Autowired
    BeerService beerService;

//    @Test
//    void getBeerById() {
////        System.out.println(beerController.getBeerById(UUID.randomUUID()));
//    }

}