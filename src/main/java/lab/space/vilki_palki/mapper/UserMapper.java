package lab.space.vilki_palki.mapper;

import lab.space.vilki_palki.entity.User;
import lab.space.vilki_palki.model.user.UserResponse;
import lab.space.vilki_palki.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserMapper {
    private final OrderService orderService;

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
}
