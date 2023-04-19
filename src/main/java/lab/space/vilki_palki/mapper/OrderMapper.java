package lab.space.vilki_palki.mapper;

import lab.space.vilki_palki.entity.Order;
import lab.space.vilki_palki.model.OrderResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderMapper {
    public List<OrderResponse> toSimplifiedListDto(List<Order> orders){
        return orders.stream().map(this::toSimplifiedDto).toList();
    }
    public OrderResponse toSimplifiedDto(Order order){
        return OrderResponse.builder()
                .id(order.getId())
                .orderCode(order.getOrderCode())
                .productsList(null)
                .date(order.getBirthday())
                .deliveryStatus(null)
                .address(null)
                .price(order.getPrice())
                .build();
    }

}
