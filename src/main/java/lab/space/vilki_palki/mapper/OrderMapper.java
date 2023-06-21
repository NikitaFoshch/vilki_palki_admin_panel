package lab.space.vilki_palki.mapper;

import lab.space.vilki_palki.entity.Order;
import lab.space.vilki_palki.model.order.OrderResponse;
import lab.space.vilki_palki.model.order.OrderResponseByPage;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public interface OrderMapper {
    static List<OrderResponse> toSimplifiedListDto(List<Order> orders) {
        return orders.stream().map(OrderMapper::toSimplifiedDto).collect(Collectors.toList());
    }

    static OrderResponse toSimplifiedDto(Order order) {
        return OrderResponse.builder()
                .id(order.getId())
                .orderCode(order.getOrderCode())
                .productsList(order.getProducts())
                .date(order.getCreateAt())
                .deliveryStatus(order.getDeliveryStatus().getValue())
                .address(order.getAddress())
                .price(order.getPrice())
                .build();
    }

    static OrderResponseByPage toOrderResponseByPage(Page<Order> orders) {
        return OrderResponseByPage.builder()
                .data(orders.stream().map(OrderMapper::toSimplifiedDto).collect(Collectors.toList()))
                .itemsCount(orders.getTotalElements())
                .pagesCount(orders.getTotalPages())
                .build();
    }

}
