package lab.space.vilki_palki.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lab.space.vilki_palki.entity.User;
import lab.space.vilki_palki.mapper.UserMapper;
import lab.space.vilki_palki.model.UserRequest;
import lab.space.vilki_palki.model.UserResponse;
import lab.space.vilki_palki.model.UserResponseByPage;
import lab.space.vilki_palki.repository.UserRepository;
import lab.space.vilki_palki.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserSpecification userSpecification;

    @Override
    public Integer getCountByAllUsers() {
        return userRepository
                        .findAll(Sort.by(Sort.Direction.DESC, "createAt")).size();
    }


    @Override
    public UserResponseByPage getUsersByPage(UserRequest userRequest) {
        final int DEFAULT_PAGE_SIZE = 5;
        return userMapper.toUserResponseByPage(
                userRepository.findAll(userSpecification.getUsersByRequest(userRequest),
                        PageRequest.of(userRequest.getPageIndex(), DEFAULT_PAGE_SIZE)));
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
