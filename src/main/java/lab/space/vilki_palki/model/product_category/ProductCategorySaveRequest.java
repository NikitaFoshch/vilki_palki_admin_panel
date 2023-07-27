package lab.space.vilki_palki.model.product_category;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Data
public class ProductCategorySaveRequest {
    @NotBlank(message = "Must be specified")
    @Size(max = 100, message = "Must be no more than {max} symbols")
    private String name;
    @NotNull(message = "Must be specified")
    private MultipartFile image;
}
