package lab.space.vilki_palki.service.impl;

import lab.space.vilki_palki.entity.Banner;
import lab.space.vilki_palki.mapper.BannerMapper;
import lab.space.vilki_palki.model.banner.BannerResponse;
import lab.space.vilki_palki.model.banner.BannerSaveRequest;
import lab.space.vilki_palki.model.banner.BannerUpdateRequest;
import lab.space.vilki_palki.repository.BannerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Sort;
import org.springframework.mock.web.MockMultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BannerServiceImplTest {

    @Mock
    private BannerRepository bannerRepository;
    @Mock
    private BannerMapper bannerMapper;
    @InjectMocks
    private BannerServiceImpl bannerService;

    @Test
    void getAllBannersByOrderByCreateAt() {
        List<Banner> banners = List.of(new Banner(), new Banner(), new Banner());
        banners.get(0).setId(1L);
        banners.get(0).setName("123");
        banners.get(0).setImage("132");
        banners.get(1).setId(2L);
        banners.get(1).setName("5435");
        banners.get(1).setImage("5734");
        banners.get(2).setId(3L);
        banners.get(2).setName("33");
        banners.get(2).setImage("4561");

        List<BannerResponse> responses = new ArrayList<>();
        responses.add(BannerResponse.builder().id(1L).name("132").image("123").build());
        responses.add(BannerResponse.builder().id(2L).name("5435").image("5734").build());
        responses.add(BannerResponse.builder().id(3L).name("33").image("4561").build());

        when(bannerRepository.findAll(Sort.by(Sort.Direction.DESC, "createAt"))).thenReturn(banners);
        List<BannerResponse> actualResponses = bannerService.getAllBannersByOrderByCreateAt();

        assertNotNull(actualResponses);
    }

    @Test
    void getBannerById() {
        Long bannerId = 1L;
        Banner banner = new Banner();
        banner.setId(bannerId);

        when(bannerRepository.findById(bannerId)).thenReturn(Optional.of(banner));

        Banner banner1 = bannerService.getBannerById(bannerId);
        assertEquals(banner, banner1);
        verify(bannerRepository, times(1)).findById(bannerId);
    }

    @Test
    void getBannerDto() {
        //  Given
        Long id = 1L;
        Banner banner = new Banner().setName("Bober").setImage("bober");
        banner.setId(1L);
        when(bannerRepository.findById(id)).thenReturn(Optional.of(banner));
        //  When
        BannerResponse response = bannerService.getBannerDto(banner.getId());
        //  Then
        assertNotNull(response);
    }

    @Test
    void saveBanner() throws Exception {
        ClassPathResource resource = new ClassPathResource("img/img.png");
        MockMultipartFile image = new MockMultipartFile("image", "image", "imag/png", resource.getInputStream());
        BannerSaveRequest request = new BannerSaveRequest();
        request.setName("Name");
        request.setImage(image);

        bannerService.saveBanner(request);

        verify(bannerRepository, times(1)).save(any(Banner.class));
    }

    @Test
    void updateBannerById() throws Exception {
        ClassPathResource resource = new ClassPathResource("img/img.png");
        MockMultipartFile image = new MockMultipartFile("image", "image", "imag/png", resource.getInputStream());
        BannerUpdateRequest request = new BannerUpdateRequest();
        request.setId(1L);
        request.setName("Name");
        request.setImage(image);
        Banner banner = new Banner().setName("name").setImage("ima");
        banner.setId(1L);

        when(bannerRepository.findById(request.getId())).thenReturn(Optional.of(banner));

        bannerService.updateBannerById(request);

        verify(bannerRepository, times(1)).save(any(Banner.class));
    }

    @Test
    void deleteBanner() throws Exception {
        Long bannerId = 1L;
        Banner banner = new Banner();
        banner.setId(bannerId);

        when(bannerRepository.findById(bannerId)).thenReturn(Optional.of(banner));

        bannerService.deleteBanner(bannerId);

        verify(bannerRepository, times(1)).findById(bannerId);
        verify(bannerRepository, times(1)).delete(banner);
    }
}