package lab.space.vilki_palki.service;

import lab.space.vilki_palki.entity.Order;
import lab.space.vilki_palki.model.OrderResponse;

import java.util.List;

public interface OrderService {
    List<OrderResponse> findAllOrdersByUserIdByOrderByCreateAt(Long id);
    Integer sumAllOrders(List<OrderResponse> orderResponses);
}
