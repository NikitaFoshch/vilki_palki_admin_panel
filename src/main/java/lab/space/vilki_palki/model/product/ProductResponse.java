package lab.space.vilki_palki.model.product;

import com.fasterxml.jackson.annotation.JsonInclude;
import lab.space.vilki_palki.model.product_category.ProductCategoryResponse;
import lab.space.vilki_palki.model.product_type.ProductTypeResponse;
import lab.space.vilki_palki.model.structure.StructureResponse;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;

@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record ProductResponse(
        Long id,
        String name,
        BigDecimal price,
        String productInfo,
        String description,
        String image,
        ProductCategoryResponse productCategory,
        ProductTypeResponse productType,
        List<StructureResponse> structures
) {
}
