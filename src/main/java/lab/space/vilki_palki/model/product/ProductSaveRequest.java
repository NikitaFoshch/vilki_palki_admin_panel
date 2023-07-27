package lab.space.vilki_palki.model.product;

import jakarta.validation.constraints.*;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;
@Data
public class ProductSaveRequest{
        @NotBlank(message = "Must be specified")
        @Size(max = 100, message = "Must be no more than {max} symbols")
        private String name;
        @NotBlank(message = "Must be specified")
        @Size(max = 500, message = "Must be no more than {max} symbols")
        private String productInfo;
        @NotNull(message = "Must be specified")
        @Digits(integer = 5, fraction = 0, message = "0-99999")
        @Min(value = 0, message = "Must be positive")
        private BigDecimal price;
        @NotBlank(message = "Must be specified")
        @Size(max = 2000, message = "Must be no more than {max} symbols")
        private String description;
        @NotNull(message = "Must be specified")
        private MultipartFile image;
        @NotNull(message = "Must be specified")
        @Min(value = 0)
        private Long productsCategoryId;
        @NotNull(message = "Must be specified")
        @Min(value = 0)
        private Long productsTypeId;
        @NotNull(message = "Must be specified")
        private List<Long> structureIds;
}
