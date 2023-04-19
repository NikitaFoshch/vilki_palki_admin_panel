package lab.space.vilki_palki.mapper;

import lab.space.vilki_palki.entity.User;
import lab.space.vilki_palki.model.UserResponse;
import lab.space.vilki_palki.service.OrderService;
import lab.space.vilki_palki.service.impl.OrderServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserMapper {
    private final OrderService orderService;
    private final OrderMapper orderMapper;
    public List<UserResponse> toSimplifiedListDto(List<User> users) {
        return users.stream().map(this::toSimplifiedDto).toList();
    }

    public List<UserResponse> toListDto(List<User> users) {
        return users.stream().map(this::toDto).toList();
    }

    public UserResponse toDto(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .birthday(user.getBirthday())
                .facebookId(user.getFacebookId())
                .phoneNumber(user.getPhone())
                .sumOrders(orderService
                        .sumAllOrders(orderService
                                .findAllOrdersByUserIdByOrderByCreateAt(user.getId())))
                .build();
    }

    public UserResponse toSimplifiedDto(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .addresses(user.getAddresses())
                .orders(user.getOrders())
                .build();
    }
}
