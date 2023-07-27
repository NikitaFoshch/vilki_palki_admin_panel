package lab.space.vilki_palki.model.product_type;

import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Data
public class ProductTypeSaveRequest {
    @NotBlank(message = "Must be specified")
    @Size(max = 100, message = "Must be no more than {max} symbols")
    private String name;
}
