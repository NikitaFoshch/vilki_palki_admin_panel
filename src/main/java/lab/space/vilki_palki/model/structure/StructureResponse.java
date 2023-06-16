package lab.space.vilki_palki.model.structure;

import com.fasterxml.jackson.annotation.JsonInclude;
import lab.space.vilki_palki.model.structure_category.StructureCategoryResponse;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class StructureResponse{
    private Long id;
    private String name;
    private StructureCategoryResponse structureCategory;
    private String weight;
    private BigDecimal price;
    private String image;
}
