package lab.space.vilki_palki.model.banner;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class BannerSaveRequest{
        @NotBlank(message = "Must be specified")
        @Size(max = 50, message = "Must be no more than {max} symbols")
        private String name;
        @NotNull(message = "Must be specified")
        private MultipartFile image;
}
