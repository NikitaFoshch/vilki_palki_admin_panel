package lab.space.vilki_palki.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LoginController.class)
@TestPropertySource(locations = "/application-test.yaml")
public class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void showLoginPage() throws Exception {
        mockMvc.perform(get("/login")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void unauthorized() throws Exception{
        mockMvc.perform(get("/banners")).andDo(print()).andExpect(status().isUnauthorized());
    }

}
