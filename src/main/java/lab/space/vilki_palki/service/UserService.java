package lab.space.vilki_palki.service;

import lab.space.vilki_palki.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAllUser();
    User getUserById(Long id);
    void deleteUserById(Long id);
 }
