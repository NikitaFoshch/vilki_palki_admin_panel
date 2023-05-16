package lab.space.vilki_palki.model.product_category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

public record ProductCategorySaveRequest(
        @NotBlank(message = "Must be specified")
        @Size(max = 100, message = "Must be no more than {max} symbols")
        String name,
        @NotNull(message = "Must be specified")
        MultipartFile image
) {
}
