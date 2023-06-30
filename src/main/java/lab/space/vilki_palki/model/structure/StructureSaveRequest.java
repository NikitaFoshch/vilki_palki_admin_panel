package lab.space.vilki_palki.model.structure;

import javax.validation.constraints.*;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
@Data
public class StructureSaveRequest{
        @NotBlank(message = "Must be specified")
        @Size(max = 100, message = "Must be no more than {max} symbols")
        private String name;
        @NotNull(message = "Must be specified")
        @Min(1)
        private Long structureCategoryId;
        @NotNull(message = "Must be specified")
        @Digits(integer = 5, fraction = 0, message = "Weight must be specified in grams(0-9999)")
        @Min(value = 0, message = "Must be positive")
        @Size(max = 5, message = "Weight must be specified in grams(0-9999)")
        private Integer weight;
        @NotNull(message = "Must be specified")
        @Digits(integer = 5, fraction = 0, message = "0-99999")
        @Min(value = 0, message = "Must be positive")
        private BigDecimal price;
        @NotNull(message = "Must be specified")
        private MultipartFile image;
}
