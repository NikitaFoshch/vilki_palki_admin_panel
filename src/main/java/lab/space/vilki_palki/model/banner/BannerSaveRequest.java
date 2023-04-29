package lab.space.vilki_palki.model.banner;

import org.springframework.web.multipart.MultipartFile;

public record BannerSaveRequest(
        String name,
        MultipartFile image
) {
}
