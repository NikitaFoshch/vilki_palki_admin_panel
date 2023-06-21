package lab.space.vilki_palki.controller;

import lab.space.vilki_palki.config.SecurityConfig;
import lab.space.vilki_palki.model.promotion.PromotionSaveRequest;
import lab.space.vilki_palki.model.promotion.PromotionUpdateRequest;
import lab.space.vilki_palki.service.ProductService;
import lab.space.vilki_palki.service.PromotionService;
import lab.space.vilki_palki.validator.ImageValidation;
import lab.space.vilki_palki.validator.PromotionValidation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpMethod;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import(SecurityConfig.class)
@WebMvcTest(PromotionsController.class)
@WithMockUser
public class PromotionsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PromotionService promotionService;
    @MockBean
    private ProductService productService;
    @MockBean
    private PromotionValidation promotionValidation;
    @MockBean
    private ImageValidation imageValidation;

    private final String DEFAULT_PATH = "/promotions";

    @Test
    void showPromotionsPage() throws Exception {
        mockMvc.perform(get(DEFAULT_PATH)).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void savePromotion() throws Exception {
        ClassPathResource resource = new ClassPathResource("img/img.png");
        MockMultipartFile image = new MockMultipartFile("image", "image", "image/png", resource.getInputStream());
        PromotionSaveRequest request = new PromotionSaveRequest();
        request.setPercent(50L);
        request.setName("Promotion");
        request.setTotalPrice(1000);
        request.setProductId(1L);
        request.setImage(image);

        mockMvc.perform(multipart(DEFAULT_PATH + "/promotion-save")
                .file(image)
                .param("percent", String.valueOf(request.getPercent()))
                .param("name", request.getName())
                .param("totalPrice", String.valueOf(request.getTotalPrice()))
                .param("productId", String.valueOf(request.getProductId()))
        ).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void updatePromotion() throws Exception {
        ClassPathResource resource = new ClassPathResource("img/img.png");
        MockMultipartFile image = new MockMultipartFile("image", "image", "image/png", resource.getInputStream());
        PromotionUpdateRequest request = new PromotionUpdateRequest();
        request.setId(1L);
        request.setPercent(50L);
        request.setName("Promotion");
        request.setTotalPrice(1000);
        request.setProductId(1L);
        request.setImage(image);

        mockMvc.perform(multipart(HttpMethod.PUT, DEFAULT_PATH + "/promotion-update")
                .file(image)
                .param("percent", String.valueOf(request.getPercent()))
                .param("name", request.getName())
                .param("totalPrice", String.valueOf(request.getTotalPrice()))
                .param("productId", String.valueOf(request.getProductId()))
                .param("id", String.valueOf(request.getId()))
        ).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void deletePromotion() throws Exception {
        mockMvc.perform(delete(DEFAULT_PATH + "/promotion-delete/" + 1)).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void getAllPromotions() throws Exception {
        mockMvc.perform(get(DEFAULT_PATH + "/get-all-promotions"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void getPromotionById() throws Exception {
        mockMvc.perform(get(DEFAULT_PATH + "/get-promotion/" + 1))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void getAllProduct() throws Exception {
        mockMvc.perform(get(DEFAULT_PATH + "/get-all-products-order-by-name"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
