package lab.space.vilki_palki.model.product;

import javax.validation.constraints.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

public record ProductUpdateRequest(
        Long id,
        @NotBlank(message = "Must be specified")
        @Size(max = 100, message = "Must be no more than {max} symbols")
        String name,
        @NotBlank(message = "Must be specified")
        @Size(max = 500, message = "Must be no more than {max} symbols")
        String productInfo,
        @NotNull(message = "Must be specified")
        @Digits(integer = 5, fraction = 0, message = "0-99999")
        @Min(value = 0, message = "Must be positive")
        BigDecimal price,
        @NotBlank(message = "Must be specified")
        @Size(max = 2000, message = "Must be no more than {max} symbols")
        String description,
        @NotNull(message = "Must be specified")
        MultipartFile image,
        @NotNull(message = "Must be specified")
        @Min(value = 0)
        Long productsCategoryId,
        @NotNull(message = "Must be specified")
        @Min(value = 0)
        Long productsTypeId,
        @NotNull(message = "Must be specified")
        List<Long> structureIds
) {
}
