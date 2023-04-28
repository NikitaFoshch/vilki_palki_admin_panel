package lab.space.vilki_palki.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lab.space.vilki_palki.entity.Banner;
import lab.space.vilki_palki.repository.BannerRepository;
import lab.space.vilki_palki.service.BannerService;
import lab.space.vilki_palki.util.FileUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class BannerServiceImpl implements BannerService {
    private final BannerRepository bannerRepository;

    @Override
    public List<Banner> getAllBannersByOrderByCreateAt() {
        log.info("---------------Get All Banners Order By createAt---------------");
        return bannerRepository.findAll(Sort.by(Sort.Direction.DESC, "createAt"));
    }

    @Override
    public Banner getBannerById(Long id) {
        log.info("---------------Get Banner By Id" + id + "---------------");
        return bannerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Banner nit found with id " + id));
    }

    @Override
    public void saveBanner(String name, MultipartFile image) {
        log.info("---------------Save Banner---------------");
        Banner banner = new Banner();
        banner.setName(name);
//        if (FileUtil.saveFile(image.getOriginalFilename(), image))
            banner.setImage(image.getOriginalFilename());
        bannerRepository.save(banner);
        log.info("---------------Success Save Banner" + banner + "---------------");

    }

    @Override
    public void updateBannerById(Long id, Banner requestBanner, MultipartFile image) {
        log.info("---------------Save Update---------------");
        Banner banner = getBannerById(id);
        banner.setName(requestBanner.getName());
//        if (FileUtil.saveFile(image.getOriginalFilename(), image))
            FileUtil.deleteFile(banner.getImage());
            banner.setImage(image.getOriginalFilename());
        bannerRepository.save(banner);
        log.info("---------------Success Update Banner" + banner + "---------------");

    }

    @Override
    public void deleteBanner(Long id) {
        log.info("---------------Save Delete---------------");
        Banner banner = getBannerById(id);
        if (banner.getImage()!=null){
            FileUtil.deleteFile(banner.getImage());
        }
        bannerRepository.delete(banner);
        log.info("---------------Success Delete Banner" + banner + "---------------");
    }
}
