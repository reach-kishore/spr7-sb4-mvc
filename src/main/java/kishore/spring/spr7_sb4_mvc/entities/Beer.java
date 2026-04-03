package kishore.spring.spr7_sb4_mvc.entities;

import jakarta.persistence.*;
import kishore.spring.spr7_sb4_mvc.model.BeerStyle;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Beer {

    @Id
//    @GeneratedValue(generator = "UUID")
//    @UuidGenerator
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(length = 36, columnDefinition = "varchar", updatable = false, nullable = false)
    private UUID id;
    @Version
    private Integer version;
    private String beerName;
    private BeerStyle beerStyle;
    private String upc;
    private Integer quantity;
    private BigDecimal price;
    private LocalDateTime createdDt;
    private LocalDateTime updateDt;
}
