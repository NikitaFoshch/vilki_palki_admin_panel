package lab.space.vilki_palki.service;

import lab.space.vilki_palki.entity.Banner;
import lab.space.vilki_palki.entity.Banner;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BannerService {
    List<Banner> getAllBannersByOrderByCreateAt();
    Banner getBannerById(Long id);
    void saveBanner(String name, MultipartFile image);
    void updateBannerById(Long id, Banner banner, MultipartFile image);
    void deleteBanner(Long id);
}
