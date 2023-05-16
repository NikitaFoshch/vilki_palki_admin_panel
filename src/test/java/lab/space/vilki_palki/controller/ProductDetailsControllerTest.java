package lab.space.vilki_palki.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lab.space.vilki_palki.config.SecurityConfig;
import lab.space.vilki_palki.model.product_category.ProductCategoryRequest;
import lab.space.vilki_palki.model.product_category.ProductCategorySaveRequest;
import lab.space.vilki_palki.model.product_category.ProductCategoryUpdateRequest;
import lab.space.vilki_palki.model.product_type.ProductTypeRequest;
import lab.space.vilki_palki.service.ProductCategoryService;
import lab.space.vilki_palki.service.ProductTypeService;
import lab.space.vilki_palki.validator.ImageValidation;
import lab.space.vilki_palki.validator.ProductCategoryValidation;
import lab.space.vilki_palki.validator.ProductTypeValidation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import(SecurityConfig.class)
@WebMvcTest(ProductDetailsController.class)
@WithMockUser
public class ProductDetailsControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ProductCategoryService productCategoryService;
    @MockBean
    private ProductCategoryValidation productCategoryValidation;
    @MockBean
    private ImageValidation imageValidation;
    @MockBean
    private ProductTypeService productTypeService;
    @MockBean
    private ProductTypeValidation productTypeValidation;

    private final String DEFAULT_PATH = "/product-details";

    @Test
    void testShowProductDetailsPage() throws Exception {
        mockMvc.perform(get(DEFAULT_PATH)).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void deleteProductCategory() throws Exception {
        mockMvc.perform(delete(DEFAULT_PATH + "/delete-product-category/" + 1))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void saveProductCategory() throws Exception {
        ClassPathResource imageResource = new ClassPathResource("img/img.png");
        MockMultipartFile image = new MockMultipartFile("image", "image", "image/png", imageResource.getInputStream());
        ProductCategorySaveRequest request = new ProductCategorySaveRequest(
                "Product 1",
                image
        );


        mockMvc.perform(multipart(DEFAULT_PATH + "/product-category-save")
                        .file(image)
                        .param("name", request.name())
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void updateProductCategory() throws Exception {
        ClassPathResource imageResource = new ClassPathResource("img/img.png");
        MockMultipartFile image = new MockMultipartFile("image", "image", "image/png", imageResource.getInputStream());
        ProductCategoryUpdateRequest request = new ProductCategoryUpdateRequest(
                1L,
                "Product 1",
                image
        );


        mockMvc.perform(multipart(HttpMethod.PUT, DEFAULT_PATH + "/product-category-update")
                        .file(image)
                        .param("name", request.name())
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void getAllProductCategories() throws Exception {
        ProductCategoryRequest request = new ProductCategoryRequest();
        request.setQuery("");
        request.setPageIndex(1);

        when(productCategoryService.getAllProductCategoriesByOrderByCreateAt(any(ProductCategoryRequest.class))).thenReturn(Page.empty());
        mockMvc.perform(
                        post(DEFAULT_PATH + "/get-all-product-categories")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request))
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void getProductCategoryById() throws Exception {
        mockMvc.perform(get(DEFAULT_PATH + "/get-product-category/" + 1))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void deleteProductType() throws Exception {
        mockMvc.perform(delete(DEFAULT_PATH + "/delete-product-type/" + 1))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void saveProductType() throws Exception {
        mockMvc.perform(post(DEFAULT_PATH + "/product-type-save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Type 1\"}")
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void updateProductType() throws Exception {
        mockMvc.perform(put(DEFAULT_PATH + "/product-type-update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":\"1\"}")
                        .content("{\"name\":\"Type 1\"}")
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void getAllProductTypes() throws Exception {
        ProductTypeRequest request = new ProductTypeRequest();
        request.setQuery("");
        request.setPageIndex(1);

        when(productTypeService.getAllProductTypesByOrderByCreateAt(any(ProductTypeRequest.class))).thenReturn(Page.empty());
        mockMvc.perform(
                        post(DEFAULT_PATH + "/get-all-product-types")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request))
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void getProductTypeById() throws Exception {
        mockMvc.perform(get(DEFAULT_PATH + "/get-product-type/" + 1))
                .andDo(print())
                .andExpect(status().isOk());
    }

}
