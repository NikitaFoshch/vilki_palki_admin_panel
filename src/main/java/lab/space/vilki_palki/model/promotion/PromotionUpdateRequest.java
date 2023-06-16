package lab.space.vilki_palki.model.promotion;

import javax.validation.constraints.*;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
@Data
public class PromotionUpdateRequest{
        private Long id;
        @NotNull(message = "Must be specified")
        @Digits(integer = 2, fraction = 0, message = "0-99")
        @Min(value = 0, message = "Must be positive")
        private Long percent;
        @NotBlank(message = "Must be specified")
        @Size(max = 100, message = "Must be no more than {max} symbols")
        private String name;
        @Min(value = 0, message = "Must be positive")
        private Integer totalPrice;
        @NotNull(message = "Must be specified")
        private MultipartFile image;
        @NotNull(message = "Must be specified")
        @Min(value = 0)
        private Long productId;
}
