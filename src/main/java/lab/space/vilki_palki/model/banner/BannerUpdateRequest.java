package lab.space.vilki_palki.model.banner;

import org.springframework.web.multipart.MultipartFile;

public record BannerUpdateRequest(
        Long id,
        String name,
        MultipartFile image
) {
}
