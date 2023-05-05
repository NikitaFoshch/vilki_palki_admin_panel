package lab.space.vilki_palki.service;

import lab.space.vilki_palki.entity.Order;
import lab.space.vilki_palki.model.order.OrderRequest;
import lab.space.vilki_palki.model.order.OrderResponse;
import lab.space.vilki_palki.model.order.OrderResponseByPage;
import org.springframework.data.domain.Page;

import java.util.List;

public interface OrderService {

    Order getOrderById(Long id);

    OrderResponseByPage getOrdersByPageByUserId(OrderRequest orderRequest);

    List<OrderResponse> findAllOrdersByUserIdByOrderByCreateAt(Long id);

    Integer sumAllOrders(List<OrderResponse> orderResponses);

    void deleteOrder(Long id);

    Page<OrderResponse> getCompletedOrders(OrderRequest orderRequest);

    Page<OrderResponse> getActiveOrders(OrderRequest orderRequest);

}
