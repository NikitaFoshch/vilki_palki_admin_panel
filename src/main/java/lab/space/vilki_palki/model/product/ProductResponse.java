package lab.space.vilki_palki.model.product;

import com.fasterxml.jackson.annotation.JsonInclude;
import lab.space.vilki_palki.entity.ProductsCategory;
import lab.space.vilki_palki.entity.ProductsType;
import lab.space.vilki_palki.entity.Structure;
import lombok.Builder;

import java.util.List;

@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record ProductResponse(
        Long id,
        String name,
        Integer price,
        String productInfo,
        String description,
        String image,
        ProductsCategory productsCategory,
        ProductsType productsType,
        List<Structure> structures
) {
}
