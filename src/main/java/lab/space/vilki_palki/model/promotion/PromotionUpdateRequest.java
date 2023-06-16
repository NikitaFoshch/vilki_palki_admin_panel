package lab.space.vilki_palki.model.promotion;

import javax.validation.constraints.*;
import org.springframework.web.multipart.MultipartFile;

public record PromotionUpdateRequest(
        Long id,
        @NotNull(message = "Must be specified")
        @Digits(integer = 2, fraction = 0, message = "0-99")
        @Min(value = 0, message = "Must be positive")
        Long percent,
        @NotBlank(message = "Must be specified")
        @Size(max = 100, message = "Must be no more than {max} symbols")
        String name,
        @Min(value = 0, message = "Must be positive")
        Integer totalPrice,
        @NotNull(message = "Must be specified")
        MultipartFile image,
        @NotNull(message = "Must be specified")
        @Min(value = 0)
        Long productId
) {
}
