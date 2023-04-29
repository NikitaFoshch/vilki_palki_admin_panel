package lab.space.vilki_palki.service;

import lab.space.vilki_palki.entity.Banner;
import lab.space.vilki_palki.model.banner.BannerResponse;
import lab.space.vilki_palki.model.banner.BannerSaveRequest;
import lab.space.vilki_palki.model.banner.BannerUpdateRequest;

import java.util.List;

public interface BannerService {
    List<BannerResponse> getAllBannersByOrderByCreateAt();
    Banner getBannerById(Long id);
    BannerResponse getBannerDto(Long id);
    void saveBanner(BannerSaveRequest request);
    void updateBannerById(BannerUpdateRequest request);
    void deleteBanner(Long id);
}
