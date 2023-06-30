package lab.space.vilki_palki.model.structure_category;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class StructureCategoryUpdateRequest {
    private Long id;
    @NotBlank(message = "Must be specified")
    @Size(max = 20, message = "Must be no more than {max} symbols")
    private String name;
}
