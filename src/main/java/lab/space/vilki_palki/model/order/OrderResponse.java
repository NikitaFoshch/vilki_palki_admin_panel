package lab.space.vilki_palki.model.order;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class OrderResponse {
    private Long id;
    private String orderCode;
    private String productsList;
    @JsonFormat(pattern = "hh:mm dd.MM.yyyy", timezone = "UTC")
    private Instant date;
    private String deliveryStatus;
    private String address;
    private BigDecimal price;

}
