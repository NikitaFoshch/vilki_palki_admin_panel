package lab.space.vilki_palki.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lab.space.vilki_palki.config.SecurityConfig;
import lab.space.vilki_palki.model.structure.StructureRequest;
import lab.space.vilki_palki.model.structure.StructureSaveRequest;
import lab.space.vilki_palki.model.structure.StructureUpdateRequest;
import lab.space.vilki_palki.service.StructureCategoryService;
import lab.space.vilki_palki.service.StructureService;
import lab.space.vilki_palki.validator.ImageValidation;
import lab.space.vilki_palki.validator.StructureValidation;
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

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import(SecurityConfig.class)
@WebMvcTest(StructureController.class)
@WithMockUser
public class StructureControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private StructureService structureService;
    @MockBean
    private StructureCategoryService structureCategoryService;
    @MockBean
    private StructureValidation structureValidation;
    @MockBean
    private ImageValidation imageValidation;

    private final String DEFAULT_PATH = "/structures";

    @Test
    void showStructurePage() throws Exception {
        mockMvc.perform(get(DEFAULT_PATH)).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void deleteStructure() throws Exception {
        mockMvc.perform(delete(DEFAULT_PATH + "/delete-structure/" + 1)).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void saveStructure() throws Exception {
        ClassPathResource resource = new ClassPathResource("img/img.png");
        MockMultipartFile image = new MockMultipartFile("image", "image", "image/png", resource.getInputStream());
        StructureSaveRequest request = new StructureSaveRequest();
        request.setName("Structure 1");
        request.setStructureCategoryId(1L);
        request.setWeight(100);
        request.setPrice(BigDecimal.valueOf(100));
        request.setImage(image);

        mockMvc.perform(
                multipart(DEFAULT_PATH + "/structure-save")
                        .file(image)
                        .param("name", request.getName())
                        .param("structureCategoryId", String.valueOf(request.getStructureCategoryId()))
                        .param("weight", String.valueOf(request.getWeight()))
                        .param("price", String.valueOf(request.getPrice()))
        ).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void updateStructure() throws Exception {
        ClassPathResource resource = new ClassPathResource("img/img.png");
        MockMultipartFile image = new MockMultipartFile("image", "image", "image/png", resource.getInputStream());
        StructureUpdateRequest request = new StructureUpdateRequest();
        request.setId(1L);
        request.setName("Structure 1");
        request.setStructureCategoryId(1L);
        request.setWeight(100);
        request.setPrice(BigDecimal.valueOf(100));
        request.setImage(image);

        mockMvc.perform(
                multipart(HttpMethod.PUT, DEFAULT_PATH + "/structure-update")
                        .file(image)
                        .param("name", request.getName())
                        .param("structureCategoryId", String.valueOf(request.getStructureCategoryId()))
                        .param("weight", String.valueOf(request.getWeight()))
                        .param("price", String.valueOf(request.getPrice()))
                        .param("price", String.valueOf(request.getId()))
        ).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void getAllStructures() throws Exception {
        StructureRequest request = new StructureRequest();
        request.setQuery("");
        request.setPageIndex(1);

        when(structureService.getAllStructuresByOrderByCreateAt(any(StructureRequest.class))).thenReturn(Page.empty());

        mockMvc.perform(
                post(DEFAULT_PATH + "/get-all-structure")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
        ).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void getAllStructureCategories() throws Exception {
        mockMvc.perform(get(DEFAULT_PATH + "/get-all-structure-categories")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void getStructureById() throws Exception {
        mockMvc.perform(get(DEFAULT_PATH + "/get-structure/" + 1)).andDo(print()).andExpect(status().isOk());
    }
}
