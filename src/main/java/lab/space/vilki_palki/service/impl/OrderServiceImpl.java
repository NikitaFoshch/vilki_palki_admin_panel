package lab.space.vilki_palki.service.impl;

import lab.space.vilki_palki.entity.Order;
import lab.space.vilki_palki.mapper.OrderMapper;
import lab.space.vilki_palki.model.order.OrderRequest;
import lab.space.vilki_palki.model.order.OrderResponse;
import lab.space.vilki_palki.model.order.OrderResponseByPage;
import lab.space.vilki_palki.repository.OrderRepository;
import lab.space.vilki_palki.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderSpecification orderSpecification;
    private final int DEFAULT_PAGE_SIZE = 10;


    @Override
    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order not found by id " + id));
    }

    @Override
    public List<Order> getAllOrders(OrderRequest request) {
        return orderRepository.findAll(orderSpecification.getOrdersByRequest(request));
    }

    @Override
    public OrderResponseByPage getOrdersByPageByUserId(OrderRequest orderRequest) {
        return OrderMapper.toOrderResponseByPage(
                orderRepository.findAll(orderSpecification.getOrdersByRequest(orderRequest),
                        PageRequest.of(orderRequest.getPageIndex(), DEFAULT_PAGE_SIZE)));
    }


    @Override
    public Page<OrderResponse> getCompletedOrders(OrderRequest orderRequest) {
        orderRequest.setDeliveryStatus(Order.DeliveryStatus.CANCELED);
        return orderRepository.findAll(orderSpecification.getOrdersByRequest(orderRequest),
                PageRequest.of(orderRequest.getPageIndex(), DEFAULT_PAGE_SIZE)).map(OrderMapper::toSimplifiedDto);
    }

    @Override
    public Page<OrderResponse> getActiveOrders(OrderRequest orderRequest) {
        orderRequest.setDeliveryStatus(Order.DeliveryStatus.ACCEPT);
        return orderRepository.findAll(orderSpecification.getOrdersByRequest(orderRequest),
                PageRequest.of(orderRequest.getPageIndex(), DEFAULT_PAGE_SIZE)).map(OrderMapper::toSimplifiedDto);
    }

    @Override
    public Integer getCountByOrdersWithDoneStatus() {
        OrderRequest request = new OrderRequest();
        request.setDeliveryStatus(Order.DeliveryStatus.DONE);
        List<Order> orders = getAllOrders(request);
        Integer count = 0;
        for (Order order : orders) {
            if (Order.DeliveryStatus.DONE == order.getDeliveryStatus()) count++;
        }
        return count;
    }

    @Override
    public Integer getCountByOrdersWithCanceledStatus() {
        OrderRequest request = new OrderRequest();
        request.setDeliveryStatus(Order.DeliveryStatus.CANCELED);
        List<Order> orders = getAllOrders(request);
        Integer count = 0;
        for (Order order : orders) {
            if (Order.DeliveryStatus.CANCELED == order.getDeliveryStatus()) count++;
        }
        return count;
    }

    @Override
    public List<Integer> getCountByOrdersWithDoneStatusByMonth() {
        OrderRequest request = new OrderRequest();
        request.setDeliveryStatus(Order.DeliveryStatus.DONE);
        List<Order> orders = getAllOrders(request);
        List<Integer> month = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            int count = 0;
            for (Order order : orders) {
                if (order.getDeliveryTime() != null) {
                    if (i == order.getDeliveryTime().atZone(ZoneId.systemDefault()).getMonthValue()
                            && Order.DeliveryStatus.DONE == order.getDeliveryStatus()) count++;
                }
            }
            month.add(count);
        }
        return month;
    }

    @Override
    public List<Long> getTotalPriceByOrdersWithDoneStatusByMonth() {
        OrderRequest request = new OrderRequest();
        request.setDeliveryStatus(Order.DeliveryStatus.DONE);
        List<Order> orders = getAllOrders(request);
        List<Long> month = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            long count = 0;
            for (Order order : orders) {
                if (order.getDeliveryTime() != null) {
                    if (i == order.getDeliveryTime().atZone(ZoneId.systemDefault()).getMonthValue()
                            && Order.DeliveryStatus.DONE == order.getDeliveryStatus()) {
                        count+=order.getPrice().longValue();
                    }
                }
            }
            month.add(count);
        }
        return month;
    }

    @Override
    public List<OrderResponse> findAllOrdersByUserIdByOrderByCreateAt(Long id) {
        log.info("---------------Get All Orders By UserId Order by create at---------------");
        return OrderMapper
                .toSimplifiedListDto(orderRepository.findAllByUserIdOrderByCreateAt(id));
    }

    @Override
    public Integer sumAllOrders(List<OrderResponse> orderResponses) {
        return orderResponses.stream()
                .map(OrderResponse::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .intValue();
    }

    @Override
    public void deleteOrder(Long id) {
        Order order = getOrderById(id);
        orderRepository.delete(order);
    }

}
