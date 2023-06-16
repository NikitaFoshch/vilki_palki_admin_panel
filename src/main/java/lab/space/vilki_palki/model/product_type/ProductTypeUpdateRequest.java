package lab.space.vilki_palki.model.product_type;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public record ProductTypeUpdateRequest(
        Long id,
        @NotBlank(message = "Must be specified")
        @Size(max = 100, message = "Must be no more than {max} symbols")
        String name
) {
}
