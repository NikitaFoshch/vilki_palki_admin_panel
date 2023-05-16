package lab.space.vilki_palki.model.structure_category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record StructureCategoryUpdateRequest(
        Long id,
        @NotBlank(message = "Must be specified")
        @Size(max = 100, message = "Must be no more than {max} symbols")
        String name
) {
}
