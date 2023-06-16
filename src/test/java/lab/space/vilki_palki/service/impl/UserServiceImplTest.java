package lab.space.vilki_palki.service.impl;

import lab.space.vilki_palki.entity.User;
import lab.space.vilki_palki.mapper.UserMapper;
import lab.space.vilki_palki.model.user.UserRequest;
import lab.space.vilki_palki.model.user.UserResponse;
import lab.space.vilki_palki.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private UserMapper userMapper;
    @Mock
    private UserSpecification userSpecification;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void getCountByAllUsers() {
        List<User> users = List.of(new User(), new User());
        when(userRepository.findAll(Sort.by(Sort.Direction.DESC, "createAt"))).thenReturn(users);

        Integer actuallyUsers = userService.getAllUsers().size();

        assertEquals(2, actuallyUsers);
    }

    @Test
    void getAllUsers() {
        List<User> users = List.of(new User(), new User());
        when(userRepository.findAll(Sort.by(Sort.Direction.DESC, "createAt"))).thenReturn(users);

        List<User> actuallyUsers = userService.getAllUsers();

        assertEquals(users, actuallyUsers);
    }

    @Test
    void getUsersByPage() {
        int pageIndex = 1;
        String query = "";
        UserRequest request = new UserRequest();
        request.setPageIndex(pageIndex);
        request.setQuery(query);

        List<User> users = new ArrayList<>();
        users.add(new User());
        users.add(new User());
        users.add(new User());
        Page<User> userPage = new PageImpl<>(users);

        when(userRepository.findAll((Specification<User>) any(), any(PageRequest.class))).thenReturn(userPage);

        Page<UserResponse> responseByPage = userService.getUsersByPage(request);

        assertNotNull(responseByPage);
        assertEquals(users.size(), responseByPage.getTotalElements());
        verify(userRepository, times(1)).findAll((Specification<User>) any(), any(PageRequest.class));


    }

    @Test
    void getUserById() {
        Long userId = 1L;
        User user = new User();
        user.setId(userId);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        User banner1 = userService.getUserById(userId);
        assertEquals(user, banner1);
        verify(userRepository, times(1)).findById(userId);

    }

    @Test
    void deleteUserById() {
        Long userId = 1L;
        User user = new User();
        user.setId(userId);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        userService.deleteUserById(userId);

        verify(userRepository, times(1)).findById(userId);
        verify(userRepository, times(1)).delete(user);
    }
}