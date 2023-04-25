package lab.space.vilki_palki.service;

import lab.space.vilki_palki.entity.Order;
import lab.space.vilki_palki.model.OrderRequest;
import lab.space.vilki_palki.model.OrderResponse;
import lab.space.vilki_palki.model.OrderResponseByPage;

import java.util.List;

public interface OrderService {
    OrderResponseByPage getOrdersByPageByUserId(OrderRequest orderRequest);
    OrderResponseByPage getOrdersByPage(OrderRequest orderRequest);
    List<OrderResponse> findAllOrdersOrderByCreateAt();
    List<OrderResponse> findAllOrdersByUserIdByOrderByCreateAt(Long id);
    Integer sumAllOrders(List<OrderResponse> orderResponses);
}
