package lab.space.vilki_palki.model.structure;

import com.fasterxml.jackson.annotation.JsonInclude;
import lab.space.vilki_palki.entity.StructureCategory;
import lombok.Builder;

@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record StructureResponse(Long id,
                                String name,
                                StructureCategory structureCategory,
                                Integer weight,
                                Integer price,
                                String image) {
}
