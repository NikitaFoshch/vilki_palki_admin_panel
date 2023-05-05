package lab.space.vilki_palki.mapper;

import lab.space.vilki_palki.entity.Banner;
import lab.space.vilki_palki.model.banner.BannerResponse;

public interface BannerMapper {

    static BannerResponse toDto(Banner banner) {
        return BannerResponse.builder()
                .id(banner.getId())
                .name(banner.getName())
                .image(banner.getImage())
                .build();
    }
}
