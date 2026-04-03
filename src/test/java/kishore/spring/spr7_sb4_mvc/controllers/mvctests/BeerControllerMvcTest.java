package kishore.spring.spr7_sb4_mvc.controllers.mvctests;

import kishore.spring.spr7_sb4_mvc.controllers.BeerController;
import kishore.spring.spr7_sb4_mvc.model.BeerDTO;
import kishore.spring.spr7_sb4_mvc.model.BeerStyle;
import kishore.spring.spr7_sb4_mvc.services.BeerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.core.Is.is;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@WebMvcTest(BeerController.class)
public class BeerControllerMvcTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    BeerService beerService;

    @Test
    void testGetBeerById() throws Exception {
        BeerDTO mockBeerDTO = BeerDTO.builder()
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

        given(beerService.getBeerbyId(any(UUID.class))).willReturn(Optional.of(mockBeerDTO));

        mockMvc.perform(get(BeerController.BEER_PATH_ID, mockBeerDTO.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(mockBeerDTO.getId().toString())))
                .andExpect(jsonPath("$.beerName", is(mockBeerDTO.getBeerName())));

        mockMvc.perform(get("/api/v1/beer/" + mockBeerDTO.getId())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(mockBeerDTO.getId().toString())))
                .andExpect(jsonPath("$.beerName", is(mockBeerDTO.getBeerName())));
    }

    @Test
    void getBeerByIdNotFound() throws Exception {

        given(beerService.getBeerbyId(any(UUID.class))).willReturn(Optional.empty());

        mockMvc.perform(get("/api/v1/beer/" + UUID.randomUUID()))
                .andExpect(status().isNotFound());
    }
}
