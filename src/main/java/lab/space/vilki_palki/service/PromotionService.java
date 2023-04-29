package lab.space.vilki_palki.service;

import lab.space.vilki_palki.entity.Promotion;
import lab.space.vilki_palki.model.promotion.PromotionResponse;
import lab.space.vilki_palki.model.promotion.PromotionSaveRequest;
import lab.space.vilki_palki.model.promotion.PromotionUpdateRequest;

import java.util.List;

public interface PromotionService {
    List<PromotionResponse> getAllPromotionsByOrderByCreateAt();

    Promotion getPromotionById(Long id);

    PromotionResponse getPromotionDto(Long id);

    void savePromotion(PromotionSaveRequest request);

    void updatePromotionById(PromotionUpdateRequest request);

    void deletePromotion(Long id);
}
