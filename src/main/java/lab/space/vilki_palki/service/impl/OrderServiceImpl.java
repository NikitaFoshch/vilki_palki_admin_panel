package lab.space.vilki_palki.service.impl;

import lab.space.vilki_palki.entity.Order;
import lab.space.vilki_palki.mapper.OrderMapper;
import lab.space.vilki_palki.model.OrderRequest;
import lab.space.vilki_palki.model.OrderResponse;
import lab.space.vilki_palki.model.OrderResponseByPage;
import lab.space.vilki_palki.repository.OrderRepository;
import lab.space.vilki_palki.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final OrderSpecification orderSpecification;


    @Override
    public List<OrderResponse> findAllOrdersOrderByCreateAt() {
        log.info("---------------Get All Orders Order by create at---------------");
        return orderMapper
                .toSimplifiedListDto(orderRepository.findAll(Sort.by(Sort.Direction.DESC, "createAt")));
    }

    @Override
    public OrderResponseByPage getOrdersByPageByUserId(OrderRequest orderRequest) {
        final int DEFAULT_PAGE_SIZE = 5;
        return orderMapper.toOrderResponseByPage(
                orderRepository.findAll(orderSpecification.getOrdersByRequestByOrderByUserId(orderRequest),
                        PageRequest.of(orderRequest.getPageIndex(), DEFAULT_PAGE_SIZE)));
    }
    @Override
    public OrderResponseByPage getOrdersByPage(OrderRequest orderRequest) {
        final int DEFAULT_PAGE_SIZE = 5;
        return orderMapper.toOrderResponseByPage(
                orderRepository.findAll(orderSpecification.getOrdersByRequest(orderRequest),
                        PageRequest.of(orderRequest.getPageIndex(), DEFAULT_PAGE_SIZE)));
    }

    @Override
    public List<OrderResponse> findAllOrdersByUserIdByOrderByCreateAt(Long id) {
        log.info("---------------Get All Orders By UserId Order by create at---------------");
        return orderMapper
                .toSimplifiedListDto(orderRepository.findAllByUserIdOrderByCreateAt(id));
    }

    @Override
    public Integer sumAllOrders(List<OrderResponse> orderResponses) {
        return orderResponses.stream()
                .mapToInt(OrderResponse::getPrice)
                .sum();
    }


}
