package lab.space.vilki_palki.model.order;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class OrderResponse {
    private Long id;
    private String orderCode;
    private String productsList;
    @JsonFormat(pattern = "hh:mm dd.MM.yyyy", timezone = "UTC")
    private Instant date;
    private DeliveryStatus deliveryStatus;
    private String address;
    private Integer price;

    public enum DeliveryStatus {
        IN_PROCESS,
        DONE
    }
}
