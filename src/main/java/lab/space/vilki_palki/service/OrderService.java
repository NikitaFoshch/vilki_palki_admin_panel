package lab.space.vilki_palki.service;

import lab.space.vilki_palki.entity.Order;
import lab.space.vilki_palki.model.order.OrderRequest;
import lab.space.vilki_palki.model.order.OrderResponse;
import lab.space.vilki_palki.model.order.OrderResponseByPage;

import java.util.List;

public interface OrderService {

    Order getOrderById(Long id);
    OrderResponseByPage getOrdersByPageByUserId(OrderRequest orderRequest);
    OrderResponseByPage getOrdersByPage(OrderRequest orderRequest);
    List<OrderResponse> findAllOrdersOrderByCreateAt();
    List<OrderResponse> findAllOrdersByUserIdByOrderByCreateAt(Long id);
    Integer sumAllOrders(List<OrderResponse> orderResponses);
    void deleteOrder(Long id);
}
