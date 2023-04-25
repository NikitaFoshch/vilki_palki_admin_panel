package lab.space.vilki_palki.service;

import lab.space.vilki_palki.entity.User;
import lab.space.vilki_palki.model.UserRequest;
import lab.space.vilki_palki.model.UserResponse;
import lab.space.vilki_palki.model.UserResponseByPage;

import java.util.List;

public interface UserService {
    Integer getCountByAllUsers();
    UserResponseByPage getUsersByPage(UserRequest userRequest);
    User getUserById(Long id);
    void deleteUserById(Long id);
 }
