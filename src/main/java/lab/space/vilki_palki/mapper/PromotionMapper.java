package lab.space.vilki_palki.mapper;

import lab.space.vilki_palki.entity.Promotion;
import lab.space.vilki_palki.model.promotion.PromotionResponse;
import org.springframework.stereotype.Component;

@Component
public class PromotionMapper {
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
                .products(promotion.getProducts())
                .totalPrice(null)
                .image(promotion.getImage())
                .build();
    }
}
