package lab.space.vilki_palki.model.promotion;

import com.fasterxml.jackson.annotation.JsonInclude;
import lab.space.vilki_palki.entity.Product;
import lombok.Builder;

import java.util.List;

@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record PromotionResponse(
        Long id,
        String name,
        Integer percent,
        Integer totalPrice,
        String image,
        List<Product> products
) {
}
