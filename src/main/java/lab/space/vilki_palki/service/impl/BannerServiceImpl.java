package lab.space.vilki_palki.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lab.space.vilki_palki.entity.Banner;
import lab.space.vilki_palki.mapper.BannerMapper;
import lab.space.vilki_palki.model.banner.BannerResponse;
import lab.space.vilki_palki.model.banner.BannerSaveRequest;
import lab.space.vilki_palki.model.banner.BannerUpdateRequest;
import lab.space.vilki_palki.repository.BannerRepository;
import lab.space.vilki_palki.service.BannerService;
import lab.space.vilki_palki.util.FileUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static java.util.Objects.nonNull;

@Service
@Slf4j
@RequiredArgsConstructor
public class BannerServiceImpl implements BannerService {
    private final BannerRepository bannerRepository;
    private final BannerMapper bannerMapper;

    @Override
    public List<BannerResponse> getAllBannersByOrderByCreateAt() {
        log.info("---------------Get All Banners Order By createAt---------------");
        return bannerRepository.findAll(Sort.by(Sort.Direction.DESC, "createAt"))
                .stream()
                .map(bannerMapper::toDto)
                .toList();
    }

    @Override
    public Banner getBannerById(Long id) {
        log.info("---------------Get Banner By Id" + id + "---------------");
        return bannerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Banner nit found with id " + id));
    }

    @Override
    public BannerResponse getBannerDto(Long id) {
        log.info("---------------Get Banner Dto By Id" + id + "---------------");
        return bannerMapper.toDto(getBannerById(id));
    }

    @Override
    public void saveBanner(BannerSaveRequest request) {
        log.info("---------------Save Banner---------------");
        Banner banner = new Banner().setName(request.name());

        if (nonNull(request.image())
                && nonNull(request.image().getOriginalFilename())
                && !request.image().getOriginalFilename().equals("")) {
            final String newFileName = UUID.randomUUID() + request.image().getOriginalFilename();
            FileUtil.saveFile(newFileName, request.image());
            banner.setImage(newFileName);
        }
        bannerRepository.save(banner);
        log.info("---------------Success Save Banner" + banner + "---------------");

    }

    @Override
    public void updateBannerById(BannerUpdateRequest request) {
        log.info("---------------Save Update---------------");
        Banner banner = getBannerById(request.id()).setName(request.name());
        if (nonNull(request.image())
                && nonNull(request.image().getOriginalFilename())
                && !request.image().getOriginalFilename().equals("")) {
            final String newFileName = UUID.randomUUID() + request.image().getOriginalFilename();
            FileUtil.saveFile(newFileName, request.image());
            FileUtil.deleteFile(banner.getImage());
            banner.setImage(newFileName);
        }
        bannerRepository.save(banner);
        log.info("---------------Success Update Banner" + banner + "---------------");

    }

    @Override
    public void deleteBanner(Long id) {
        log.info("---------------Save Delete---------------");
        Banner banner = getBannerById(id);
        if (nonNull(banner.getImage())) FileUtil.deleteFile(banner.getImage());
        bannerRepository.delete(banner);
        log.info("---------------Success Delete Banner" + banner + "---------------");
    }
}
