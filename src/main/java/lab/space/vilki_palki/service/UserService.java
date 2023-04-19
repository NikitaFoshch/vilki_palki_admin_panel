package lab.space.vilki_palki.service;

import lab.space.vilki_palki.entity.User;
import lab.space.vilki_palki.model.UserResponse;

import java.util.List;

public interface UserService {
    List<UserResponse> getAllUsers();
    User getUserById(Long id);
    void deleteUserById(Long id);
 }
