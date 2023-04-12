package lab.space.vilki_palki.service;

import lab.space.vilki_palki.entity.User;

public interface UserService {
    User getUserByEmail(String email);
}
