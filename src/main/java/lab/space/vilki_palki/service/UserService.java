package lab.space.vilki_palki.service;

import lab.space.vilki_palki.entity.User;
import lab.space.vilki_palki.model.user.UserRequest;
import lab.space.vilki_palki.model.user.UserResponse;
import org.springframework.data.domain.Page;

public interface UserService {
    Integer getCountByAllUsers();

    Page<UserResponse> getUsersByPage(UserRequest userRequest);

    User getUserById(Long id);

    void deleteUserById(Long id);
}
