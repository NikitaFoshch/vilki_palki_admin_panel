package lab.space.vilki_palki.service.impl;

import lab.space.vilki_palki.mapper.OrderMapper;
import lab.space.vilki_palki.model.OrderResponse;
import lab.space.vilki_palki.repository.OrderRepository;
import lab.space.vilki_palki.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl  implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

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
