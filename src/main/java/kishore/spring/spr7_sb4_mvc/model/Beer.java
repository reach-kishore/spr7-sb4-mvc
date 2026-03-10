package kishore.spring.spr7_sb4_mvc.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@NoArgsConstructor // Required by JPA specification
@AllArgsConstructor
@Builder
@Data
public class Beer {

    @Id
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
