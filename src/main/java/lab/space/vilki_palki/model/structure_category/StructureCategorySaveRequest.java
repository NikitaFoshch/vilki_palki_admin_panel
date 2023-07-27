package lab.space.vilki_palki.model.structure_category;

import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Data
public class StructureCategorySaveRequest {
    @NotBlank(message = "Must be specified")
    @Size(max = 20, message = "Must be no more than {max} symbols")
    private String name;
}
