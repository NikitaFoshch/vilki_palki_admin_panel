package lab.space.vilki_palki.service.impl;

import jakarta.persistence.EntityNotFoundException;
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

import java.math.BigDecimal;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderSpecification orderSpecification;
    private final int DEFAULT_PAGE_SIZE = 5;


    @Override
    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order not found by id " + id));
    }

    @Override
    public OrderResponseByPage getOrdersByPageByUserId(OrderRequest orderRequest) {
        return OrderMapper.toOrderResponseByPage(
                orderRepository.findAll(orderSpecification.getOrdersByRequest(orderRequest),
                        PageRequest.of(orderRequest.getPageIndex(), DEFAULT_PAGE_SIZE)));
    }


    @Override
    public Page<OrderResponse> getCompletedOrders(OrderRequest orderRequest) {
        return orderRepository.findAllByCompletedDeliveryStatus(orderSpecification.getOrdersByRequest(orderRequest),
                PageRequest.of(orderRequest.getPageIndex(), DEFAULT_PAGE_SIZE)).map(OrderMapper::toSimplifiedDto);
    }

    @Override
    public Page<OrderResponse> getActiveOrders(OrderRequest orderRequest) {
        return orderRepository.findAllByActiveDeliveryStatus(orderSpecification.getOrdersByRequest(orderRequest),
                PageRequest.of(orderRequest.getPageIndex(), DEFAULT_PAGE_SIZE)).map(OrderMapper::toSimplifiedDto);
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
