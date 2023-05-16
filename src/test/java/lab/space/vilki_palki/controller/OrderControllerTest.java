package lab.space.vilki_palki.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import lab.space.vilki_palki.config.SecurityConfig;
import lab.space.vilki_palki.model.order.OrderRequest;
import lab.space.vilki_palki.repository.OrderRepository;
import lab.space.vilki_palki.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import(SecurityConfig.class)
@WebMvcTest(OrderController.class)
@WithMockUser
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private OrderService orderService;
    @MockBean
    private OrderRepository orderRepository;

    @Test
    void testShowOrderPage() throws Exception {
        mockMvc.perform(get("/orders")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void testGetActiveOrdersByRequest() throws Exception {
        OrderRequest request = new OrderRequest();
        request.setQuery("");
        request.setPageIndex(1);

        when(orderService.getCompletedOrders(any(OrderRequest.class))).thenReturn(Page.empty());

        mockMvc.perform(
                        post("/orders/get-active-orders-by-request")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request))
                )
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    void testGetCompleteOrdersByRequest() throws Exception {
        OrderRequest request = new OrderRequest();
        request.setQuery("");
        request.setPageIndex(1);

        when(orderService.getCompletedOrders(any(OrderRequest.class))).thenReturn(Page.empty());

        mockMvc.perform(
                        post("/orders/get-completed-orders-by-request")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request))
                )
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    void testDeleteOrderById() throws Exception {
        mockMvc.perform(delete("/orders/delete-order/" + 1))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
