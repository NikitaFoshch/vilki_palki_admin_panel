package lab.space.vilki_palki.mapper;

import lab.space.vilki_palki.entity.Order;
import lab.space.vilki_palki.model.OrderResponse;
import lab.space.vilki_palki.model.OrderResponseByPage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderMapper {
    public List<OrderResponse> toSimplifiedListDto(List<Order> orders) {
        return orders.stream().map(this::toSimplifiedDto).toList();
    }

    public OrderResponse toSimplifiedDto(Order order) {
        return OrderResponse.builder()
                .id(order.getId())
                .orderCode(order.getOrderCode())
                .productsList(order.getProducts())
                .date(order.getCreateAt())
                .deliveryStatus(null)
                .address(order.getAddress())
                .price(order.getPrice())
                .build();
    }

    public OrderResponseByPage toOrderResponseByPage(Page<Order> order) {
        return OrderResponseByPage.builder()
                .data(order.stream().map(this::toSimplifiedDto).toList())
                .itemsCount(order.getTotalElements())
                .pagesCount(order.getTotalPages())
                .build();
    }

}
