package lab.space.vilki_palki.service;

import lab.space.vilki_palki.model.order.OrderRequest;
import lab.space.vilki_palki.model.order.OrderResponse;
import lab.space.vilki_palki.model.order.OrderResponseByPage;

import java.util.List;

public interface OrderService {
    OrderResponseByPage getOrdersByPageByUserId(OrderRequest orderRequest);
    OrderResponseByPage getOrdersByPage(OrderRequest orderRequest);
    List<OrderResponse> findAllOrdersOrderByCreateAt();
    List<OrderResponse> findAllOrdersByUserIdByOrderByCreateAt(Long id);
    Integer sumAllOrders(List<OrderResponse> orderResponses);
}
