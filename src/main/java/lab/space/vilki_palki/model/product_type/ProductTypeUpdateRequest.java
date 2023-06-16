package lab.space.vilki_palki.model.product_type;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class ProductTypeUpdateRequest {
    private Long id;
    @NotBlank(message = "Must be specified")
    @Size(max = 100, message = "Must be no more than {max} symbols")
    private String name;
}
