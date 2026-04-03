package kishore.spring.spr7_sb4_mvc.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Data
public class BeerDTO {

    private UUID id;
    private Integer version;
    private String beerName;
    private BeerStyle beerStyle;
    private String upc;
    private Integer quantity;
    private BigDecimal price;
    private LocalDateTime createdDt;
    private LocalDateTime updateDt;
}
