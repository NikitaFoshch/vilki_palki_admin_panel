package lab.space.vilki_palki.model.structure_category;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record StructureCategoryResponse(
        Long id,
        String name
) {
}
