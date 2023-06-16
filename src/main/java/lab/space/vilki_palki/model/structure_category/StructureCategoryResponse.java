package lab.space.vilki_palki.model.structure_category;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class StructureCategoryResponse {
    private Long id;
    private String name;
}
