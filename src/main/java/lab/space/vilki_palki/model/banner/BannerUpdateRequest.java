package lab.space.vilki_palki.model.banner;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

public record BannerUpdateRequest(
        Long id,
        @NotBlank(message = "Must be specified")
        @Size(max = 50, message = "Must be no more than {max} symbols")
        String name,
        @NotNull(message = "Must be specified")
        MultipartFile image
) {
}
