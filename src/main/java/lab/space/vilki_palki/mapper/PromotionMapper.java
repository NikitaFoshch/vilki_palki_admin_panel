package lab.space.vilki_palki.mapper;

import lab.space.vilki_palki.entity.Promotion;
import lab.space.vilki_palki.model.promotion.PromotionResponse;
import lab.space.vilki_palki.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PromotionMapper {
    private final ProductService productService;

    public PromotionResponse toSimpleDto(Promotion promotion) {
        return PromotionResponse.builder()
                .id(promotion.getId())
                .name(promotion.getName())
                .image(promotion.getImage())
                .build();
    }

    public PromotionResponse toDto(Promotion promotion) {
        return PromotionResponse.builder()
                .id(promotion.getId())
                .name(promotion.getName())
                .percent(promotion.getPercent())
                .product(productService.getProductSimpleDto(promotion.getProduct().getId()))
                .totalPrice(promotion.getPercent())
                .image(promotion.getImage())
                .build();
    }
}
