package kishore.spring.spr7_sb4_mvc.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Customer {

    private Integer customerId;
    private String customerName;
    private String version;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;

}
