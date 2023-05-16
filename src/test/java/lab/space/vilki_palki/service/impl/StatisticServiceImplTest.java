package lab.space.vilki_palki.service.impl;

import lab.space.vilki_palki.entity.User;
import lab.space.vilki_palki.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
class StatisticServiceImplTest {
    @Mock
    private UserService userService;

    @InjectMocks
    private StatisticServiceImpl statisticService;

    @Test
    void getAllBirthMonth() {
        List<User> users = List.of(new User(), new User(), new User());
        users.get(0).setId(1L);
        users.get(0).setName("123");
        users.get(0).setBirthday(Instant.now());
        users.get(1).setId(2L);
        users.get(1).setName("5435");
        users.get(1).setBirthday(Instant.now());
        users.get(2).setId(3L);
        users.get(2).setName("33");
        users.get(2).setBirthday(Instant.now());

        when(userService.getAllUsers()).thenReturn(users);
        List<Integer> actualResponses = statisticService.getAllBirthMonth();
        System.out.println(actualResponses);
        assertNotNull(actualResponses);
    }
}