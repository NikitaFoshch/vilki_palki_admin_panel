package lab.space.vilki_palki.model.product_category;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class ProductCategoryUpdateRequest {
    private Long id;
    @NotBlank(message = "Must be specified")
    @Size(max = 100, message = "Must be no more than {max} symbols")
    private String name;
    private MultipartFile image;
}
