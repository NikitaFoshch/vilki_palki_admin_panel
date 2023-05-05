package lab.space.vilki_palki.model.product_category;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record ProductCategoryResponse(
        Long id,
        String name
) {
}
