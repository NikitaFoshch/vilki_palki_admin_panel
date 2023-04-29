package lab.space.vilki_palki.mapper;

import lab.space.vilki_palki.entity.Banner;
import lab.space.vilki_palki.model.banner.BannerResponse;
import org.springframework.stereotype.Component;

@Component
public class BannerMapper {

    public BannerResponse toDto(Banner banner) {
        return BannerResponse.builder()
                .id(banner.getId())
                .name(banner.getName())
                .image(banner.getImage())
                .build();
    }
}
