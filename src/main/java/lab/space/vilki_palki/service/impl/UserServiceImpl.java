package lab.space.vilki_palki.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lab.space.vilki_palki.entity.User;
import lab.space.vilki_palki.repository.UserRepository;
import lab.space.vilki_palki.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll(Sort.by(Sort.Direction.DESC, "createAt"));
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id - " + id));
    }

    @Override
    public void deleteUserById(Long id) {
        User user = getUserById(id);
        userRepository.delete(user);
    }

}
