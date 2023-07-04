package lab.space.vilki_palki.model.order;

import lab.space.vilki_palki.entity.Order;
import lombok.Data;

@Data
public class OrderRequest {
    private Integer pageIndex;
    private String query;
    private Long userId;
    private Order.DeliveryStatus deliveryStatus;
}
