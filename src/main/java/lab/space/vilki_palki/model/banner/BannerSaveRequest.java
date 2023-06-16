package lab.space.vilki_palki.model.banner;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

public record BannerSaveRequest(
        @NotBlank(message = "Must be specified")
        @Size(max = 50, message = "Must be no more than {max} symbols")
        String name,
        @NotNull(message = "Must be specified")
        MultipartFile image
) {
}
