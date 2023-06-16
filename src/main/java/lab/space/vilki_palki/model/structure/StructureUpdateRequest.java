package lab.space.vilki_palki.model.structure;

import javax.validation.constraints.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

public record StructureUpdateRequest(
        Long id,
        @NotBlank(message = "Must be specified")
        @Size(max = 100, message = "Must be no more than {max} symbols")
        String name,
        @NotNull(message = "Must be specified")
        @Min(1)
        Long structureCategoryId,
        @NotNull(message = "Must be specified")
        @Digits(integer = 5, fraction = 0, message = "Weight must be specified in grams")
        @Min(value = 0, message = "Must be positive")
        Integer weight,
        @NotNull(message = "Must be specified")
        @Digits(integer = 5, fraction = 0, message = "0-99999")
        @Min(value = 0, message = "Must be positive")
        BigDecimal price,
        @NotNull(message = "Must be specified")
        MultipartFile image
) {
}
