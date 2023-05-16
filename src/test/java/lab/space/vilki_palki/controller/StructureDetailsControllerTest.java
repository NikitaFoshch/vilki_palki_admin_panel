package lab.space.vilki_palki.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lab.space.vilki_palki.config.SecurityConfig;
import lab.space.vilki_palki.model.structure_category.StructureCategoryRequest;
import lab.space.vilki_palki.service.StructureCategoryService;
import lab.space.vilki_palki.validator.StructureCategoryValidation;
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
@WebMvcTest(StructureDetailsController.class)
@WithMockUser
public class StructureDetailsControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private StructureCategoryService structureCategoryService;
    @MockBean
    private StructureCategoryValidation structureCategoryValidation;

    private final String DEFAULT_PATH = "/structure-details";

    @Test
    void showStructureDetailsPage() throws Exception {
        mockMvc.perform(get(DEFAULT_PATH)).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void deleteStructureCategory() throws Exception {
        mockMvc.perform(delete(DEFAULT_PATH + "/delete-structure-category/" + 1)).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void saveStructureCategory() throws Exception {
        mockMvc.perform(
                post(DEFAULT_PATH + "/structure-category-save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"StructureCategory\"}")
        ).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void updateStructureCategory() throws Exception {
        mockMvc.perform(
                put(DEFAULT_PATH + "/structure-category-update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"StructureCategory\"}")
        ).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void getAllStructureCategories() throws Exception {
        StructureCategoryRequest request = new StructureCategoryRequest();

        when(structureCategoryService.getAllStructureCategoriesByOrderByCreateAt(any(StructureCategoryRequest.class))).thenReturn(Page.empty());

        mockMvc.perform(
                post(DEFAULT_PATH + "/get-all-structure-categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
        ).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void getStructureById() throws Exception {
        mockMvc.perform(get(DEFAULT_PATH + "/get-structure-category/" + 1)).andDo(print()).andExpect(status().isOk());
    }
}
