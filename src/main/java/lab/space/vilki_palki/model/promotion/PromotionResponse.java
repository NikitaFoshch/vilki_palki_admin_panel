package lab.space.vilki_palki.model.promotion;

import com.fasterxml.jackson.annotation.JsonInclude;
import lab.space.vilki_palki.model.product.ProductResponse;
import lombok.Builder;

@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record PromotionResponse(
        Long id,
        String name,
        Long percent,
        Long totalPrice,
        String image,
        ProductResponse product
) {
}
