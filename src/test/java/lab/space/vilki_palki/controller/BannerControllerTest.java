package lab.space.vilki_palki.controller;

import lab.space.vilki_palki.config.SecurityConfig;
import lab.space.vilki_palki.model.banner.BannerSaveRequest;
import lab.space.vilki_palki.model.banner.BannerUpdateRequest;
import lab.space.vilki_palki.repository.BannerRepository;
import lab.space.vilki_palki.service.BannerService;
import lab.space.vilki_palki.validator.BannerValidation;
import lab.space.vilki_palki.validator.ImageValidation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpMethod;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@Import(SecurityConfig.class)
@WebMvcTest(BannerController.class)
@TestPropertySource(locations = "/application-test.yaml")
@WithMockUser
public class BannerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BannerService bannerService;
    @MockBean
    private BannerValidation bannerValidation;
    @MockBean
    private ImageValidation imageValidation;
    @MockBean
    private BannerRepository bannerRepository;

    @Test
    void testShowBannerPage() throws Exception {
        mockMvc.perform(get("/banners"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void testSaveBanner() throws Exception {
        ClassPathResource imageResource = new ClassPathResource("img/img.png");
        MockMultipartFile image = new MockMultipartFile("image", "image", "image/png", imageResource.getInputStream());
        BannerSaveRequest request = new BannerSaveRequest("Banner 1", image);

        mockMvc.perform(
                        multipart("/banners/banner-save")
                                .file(image)
                                .param("name", request.name())
                )
                .andExpect(status().isOk());
    }


    @Test
    void testUpdateBanner() throws Exception {
        ClassPathResource imageResource = new ClassPathResource("img/img.png");
        MockMultipartFile image = new MockMultipartFile("image", "image", "image/png", imageResource.getInputStream());
        BannerUpdateRequest request = new BannerUpdateRequest(1L, "Banner 2", image);

        mockMvc.perform(
                        multipart(HttpMethod.PUT, "/banners/banner-update")
                                .file(image)
                                .param("name", request.name())
                                .param("id", String.valueOf(request.id()))
                )
                .andExpect(status().isOk());
    }

    @Test
    void testGetAllBanners() throws Exception {
        mockMvc.perform(get("/banners/get-all-banners"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void testGetBannerById() throws Exception {
        mockMvc.perform(get("/banners/get-banner/" + 1))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteBannerById() throws Exception {
        mockMvc.perform(delete("/banners/banner-delete/" + 1))
                .andDo(print())
                .andExpect(status().isOk());
    }
}