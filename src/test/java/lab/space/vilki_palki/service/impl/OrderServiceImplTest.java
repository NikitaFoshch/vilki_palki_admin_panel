package lab.space.vilki_palki.service.impl;

import lab.space.vilki_palki.entity.Order;
import lab.space.vilki_palki.model.order.OrderRequest;
import lab.space.vilki_palki.model.order.OrderResponse;
import lab.space.vilki_palki.model.order.OrderResponseByPage;
import lab.space.vilki_palki.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {

    @Mock
    private OrderRepository orderRepository;
    @Mock
    private OrderSpecification orderSpecification;

    @InjectMocks
    private OrderServiceImpl orderService;
    @InjectMocks
    private OrderResponse orderResponse;

    @Test
    void getOrderById() {
        Long orderId = 1L;
        Order order = new Order();
        order.setId(orderId);

        when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));

        Order order1 = orderService.getOrderById(orderId);
        assertEquals(order, order1);
        verify(orderRepository, times(1)).findById(orderId);
    }

    @Test
    void getOrdersByPageByUserId() {
        int pageIndex = 1;
        String query = "";
        OrderRequest request = new OrderRequest();
        request.setPageIndex(pageIndex);
        request.setQuery(query);

        List<Order> orders = new ArrayList<>();
        orders.add(new Order());
        orders.add(new Order());
        orders.add(new Order());
        orders.get(0).setDeliveryStatus(Order.DeliveryStatus.DONE);
        orders.get(1).setDeliveryStatus(Order.DeliveryStatus.DONE);
        orders.get(2).setDeliveryStatus(Order.DeliveryStatus.DONE);
        Page<Order> orderPage = new PageImpl<>(orders);

        when(orderRepository.findAll((Specification<Order>) any(), any(PageRequest.class))).thenReturn(orderPage);

        OrderResponseByPage responseByPage = orderService.getOrdersByPageByUserId(request);

        assertNotNull(responseByPage);
        assertEquals(orders.size(), responseByPage.getData().size());
        verify(orderRepository, times(1)).findAll((Specification<Order>) any(), any(PageRequest.class));
    }

    @Test
    void getCompletedOrders() {
        int pageIndex = 1;
        String query = "";
        OrderRequest request = new OrderRequest();
        request.setPageIndex(pageIndex);
        request.setQuery(query);

        List<Order> orders = new ArrayList<>();
        orders.add(new Order());
        orders.add(new Order());
        orders.add(new Order());
        orders.get(0).setDeliveryStatus(Order.DeliveryStatus.DONE);
        orders.get(1).setDeliveryStatus(Order.DeliveryStatus.DONE);
        orders.get(2).setDeliveryStatus(Order.DeliveryStatus.DONE);
        Page<Order> orderPage = new PageImpl<>(orders);

        when(orderRepository.findAll((Specification<Order>) any(), any(PageRequest.class))).thenReturn(orderPage);

        Page<OrderResponse> responseByPage = orderService.getCompletedOrders(request);

        assertNotNull(responseByPage);
        assertEquals(orders.size(), responseByPage.getTotalElements());
        verify(orderRepository, times(1)).findAll((Specification<Order>) any(), any(PageRequest.class));
    }

    @Test
    void getActiveOrders() {
        int pageIndex = 1;
        String query = "";
        OrderRequest request = new OrderRequest();
        request.setPageIndex(pageIndex);
        request.setQuery(query);

        List<Order> orders = new ArrayList<>();
        orders.add(new Order());
        orders.add(new Order());
        orders.add(new Order());
        orders.get(0).setDeliveryStatus(Order.DeliveryStatus.IN_PROCESS);
        orders.get(1).setDeliveryStatus(Order.DeliveryStatus.IN_PROCESS);
        orders.get(2).setDeliveryStatus(Order.DeliveryStatus.IN_PROCESS);
        Page<Order> orderPage = new PageImpl<>(orders);

        when(orderRepository.findAll((Specification<Order>) any(), any(PageRequest.class))).thenReturn(orderPage);

        Page<OrderResponse> responseByPage = orderService.getActiveOrders(request);

        assertNotNull(responseByPage);
        assertEquals(orders.size(), responseByPage.getTotalElements());
        verify(orderRepository, times(1)).findAll((Specification<Order>) any(), any(PageRequest.class));
    }

    @Test
    void findAllOrdersByUserIdByOrderByCreateAt() {
        Long userId = 1L;

        List<Order> orders = new ArrayList<>();
        orders.add(new Order());
        orders.add(new Order());
        orders.add(new Order());
        orders.get(0).setDeliveryStatus(Order.DeliveryStatus.DONE);
        orders.get(1).setDeliveryStatus(Order.DeliveryStatus.DONE);
        orders.get(2).setDeliveryStatus(Order.DeliveryStatus.DONE);

        when(orderRepository.findAllByUserIdOrderByCreateAt(userId)).thenReturn(orders);

        List<OrderResponse> response = orderService.findAllOrdersByUserIdByOrderByCreateAt(userId);

        assertNotNull(response);
        assertEquals(orders.size(), response.size());
        verify(orderRepository, times(1)).findAllByUserIdOrderByCreateAt(userId);
    }

    @Test
    void sumAllOrders() {
        Long userId = 1L;
        List<OrderResponse> orders = new ArrayList<>();
        orders.add(orderResponse);
        orders.add(orderResponse);
        orders.get(1).setDeliveryStatus("Done");
        orders.get(1).setPrice(BigDecimal.valueOf(2));
        System.out.println(orders);

        Integer response = orderService.sumAllOrders(orders);

        assertNotNull(response);
        assertEquals(4, response);
    }

    @Test
    void deleteOrder() {
        Long orderId = 1L;
        Order order = new Order();
        order.setId(orderId);

        when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));

        orderService.deleteOrder(orderId);

        verify(orderRepository, times(1)).findById(orderId);
        verify(orderRepository, times(1)).delete(order);
    }

    @Test
    void testGetCountByOrdersWithDoneStatus() {
        List<Order> orders = new ArrayList<>();
        orders.add(new Order().setDeliveryStatus(Order.DeliveryStatus.DONE));
        orders.add(new Order().setDeliveryStatus(Order.DeliveryStatus.DONE));
        orders.add(new Order().setDeliveryStatus(Order.DeliveryStatus.CANCELED));

        when(orderRepository.findAll(orderSpecification.getOrdersByRequest(Mockito.any()))).thenReturn(orders);

        Integer count = orderService.getCountByOrdersWithDoneStatus();

        assertEquals(2, count);
    }

    @Test
    void testGetCountByOrdersWithCanceledStatus() {
        List<Order> orders = new ArrayList<>();
        orders.add(new Order().setDeliveryStatus(Order.DeliveryStatus.DONE));
        orders.add(new Order().setDeliveryStatus(Order.DeliveryStatus.DONE));
        orders.add(new Order().setDeliveryStatus(Order.DeliveryStatus.CANCELED));

        when(orderRepository.findAll(orderSpecification.getOrdersByRequest(Mockito.any()))).thenReturn(orders);

        Integer count = orderService.getCountByOrdersWithCanceledStatus();

        assertEquals(1, count);
    }

    @Test
    void testGetCountByOrdersWithDoneStatusByMonth() {
        List<Order> orders = new ArrayList<>();
        Instant deliveryTime = Instant.now();
        orders.add(new Order().setDeliveryStatus(Order.DeliveryStatus.DONE).setDeliveryTime(Instant.from(deliveryTime.plusSeconds(1000))));
        orders.add(new Order().setDeliveryStatus(Order.DeliveryStatus.DONE).setDeliveryTime(Instant.from(deliveryTime.plusSeconds(1000))));
        orders.add(new Order().setDeliveryStatus(Order.DeliveryStatus.DONE).setDeliveryTime(Instant.from(deliveryTime.plusSeconds(2000))));
        orders.add(new Order().setDeliveryStatus(Order.DeliveryStatus.CANCELED).setDeliveryTime(Instant.from(deliveryTime.plusSeconds(2000))));

        when(orderRepository.findAll(orderSpecification.getOrdersByRequest(Mockito.any()))).thenReturn(orders);

        List<Integer> counts = orderService.getCountByOrdersWithDoneStatusByMonth();

        assertEquals(12, counts.size());
        assertEquals(0, counts.get(1));
        assertEquals(0, counts.get(2));
    }

    @Test
    void testGetTotalPriceByOrdersWithDoneStatusByMonth() {
        List<Order> orders = new ArrayList<>();
        Instant deliveryTime = Instant.now();
        orders.add(new Order().setDeliveryStatus(Order.DeliveryStatus.DONE).setDeliveryTime(Instant.from(deliveryTime.plusSeconds(1000))).setPrice(BigDecimal.valueOf(100)));
        orders.add(new Order().setDeliveryStatus(Order.DeliveryStatus.DONE).setDeliveryTime(Instant.from(deliveryTime.plusSeconds(1000))).setPrice(BigDecimal.valueOf(200)));
        orders.add(new Order().setDeliveryStatus(Order.DeliveryStatus.DONE).setDeliveryTime(Instant.from(deliveryTime.plusSeconds(2000))).setPrice(BigDecimal.valueOf(300)));
        orders.add(new Order().setDeliveryStatus(Order.DeliveryStatus.CANCELED).setDeliveryTime(Instant.from(deliveryTime.plusSeconds(2000))).setPrice(BigDecimal.valueOf(400)));

        when(orderRepository.findAll(orderSpecification.getOrdersByRequest(Mockito.any()))).thenReturn(orders);

        List<Long> prices = orderService.getTotalPriceByOrdersWithDoneStatusByMonth();

        assertEquals(12, prices.size());
        assertEquals(0, prices.get(1).longValue());
        assertEquals(0, prices.get(2).longValue());
    }

    @Test
    void testGetAllOrders() {
        List<Order> orders = new ArrayList<>();
        orders.add(new Order().setDeliveryStatus(Order.DeliveryStatus.DONE));
        orders.add(new Order().setDeliveryStatus(Order.DeliveryStatus.DONE));
        orders.add(new Order().setDeliveryStatus(Order.DeliveryStatus.CANCELED));

        OrderRequest request = new OrderRequest();

        when(orderRepository.findAll(orderSpecification.getOrdersByRequest(request))).thenReturn(orders);

        List<Order> result = orderService.getAllOrders(request);

        assertEquals(orders, result);
    }
}