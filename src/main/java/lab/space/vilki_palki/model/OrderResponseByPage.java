package lab.space.vilki_palki.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class OrderResponseByPage{
    private List<OrderResponse> data;
    private Long itemsCount;
    private Integer pagesCount;
}