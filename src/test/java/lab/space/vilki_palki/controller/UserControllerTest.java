package lab.space.vilki_palki.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lab.space.vilki_palki.config.SecurityConfig;
import lab.space.vilki_palki.model.user.UserRequest;
import lab.space.vilki_palki.service.AddressService;
import lab.space.vilki_palki.service.AdminService;
import lab.space.vilki_palki.service.OrderService;
import lab.space.vilki_palki.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import(SecurityConfig.class)
@WebMvcTest(UserController.class)
@WithMockUser
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;
    @MockBean
    private AdminService adminService;
    @MockBean
    private OrderService orderService;
    @MockBean
    private AddressService addressService;

    private final String DEFAULT_PATH = "/users";

    @Test
    void showUserPage() throws Exception {
        mockMvc.perform(get(DEFAULT_PATH)).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void getAllUsers() throws Exception {
        UserRequest request = new UserRequest();

        mockMvc.perform(
                post(DEFAULT_PATH + "/get-all-users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
        ).andDo(print()).andExpect(status().isOk());


    }

    @Test
    void getAllAdmins() throws Exception {
        mockMvc.perform(
                post(DEFAULT_PATH + "/get-all-admins")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"page\":1, \"query\":\"\"}")
        ).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void deleteUser() throws Exception {
        mockMvc.perform(delete(DEFAULT_PATH + "/delete-user/" + 1))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void showUserDetailsPage() throws Exception {
        mockMvc.perform(get(DEFAULT_PATH + "/user-details/" + 1))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void deleteAdmin() throws Exception {
        mockMvc.perform(delete(DEFAULT_PATH + "/delete-admin/" + 1))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void getAllOrdersByUserId() throws Exception {
        mockMvc.perform(
                post(DEFAULT_PATH + "/get-all-orders-by-user-id")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"page\":1, \"query\":\"\"}")
        ).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void getAllAddressByUserId() throws Exception {
        mockMvc.perform(
                post(DEFAULT_PATH + "/get-all-address-by-user-id")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"page\":1, \"query\":\"\"}")
        ).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void deleteOrder() throws Exception {
        mockMvc.perform(delete(DEFAULT_PATH + "/delete-order/" + 1))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void deleteAddress() throws Exception {
        mockMvc.perform(delete(DEFAULT_PATH + "/delete-address/" + 1))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
