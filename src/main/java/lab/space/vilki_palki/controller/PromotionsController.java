package lab.space.vilki_palki.controller;

import lab.space.vilki_palki.model.banner.BannerResponse;
import lab.space.vilki_palki.model.banner.BannerSaveRequest;
import lab.space.vilki_palki.model.banner.BannerUpdateRequest;
import lab.space.vilki_palki.model.promotion.PromotionResponse;
import lab.space.vilki_palki.model.promotion.PromotionSaveRequest;
import lab.space.vilki_palki.model.promotion.PromotionUpdateRequest;
import lab.space.vilki_palki.service.PromotionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/promotions")
@RequiredArgsConstructor
public class PromotionsController {

    private final PromotionService promotionService;

    @GetMapping({"/", ""})
    public String showPromotionsPage() {
        return "/admin-panel/pages/promotions/promotions";
    }

    @PostMapping("promotion-save")
    public ResponseEntity<?> savePromotion(@ModelAttribute PromotionSaveRequest request){
        promotionService.savePromotion(request);
        return ResponseEntity.ok().build();
    }
    @PutMapping("promotion-update")
    public ResponseEntity<?> updatePromotion(@ModelAttribute PromotionUpdateRequest request){
        promotionService.updatePromotionById(request);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("promotion-delete/{id}")
    public ResponseEntity<?> deletePromotion(@PathVariable Long id){
        promotionService.deletePromotion(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("get-all-promotions")
    public ResponseEntity<List<PromotionResponse>> getAllPromotions() {
        return ResponseEntity.ok(promotionService.getAllPromotionsByOrderByCreateAt());
    }

    @GetMapping("get-promotion/{id}")
    public ResponseEntity<PromotionResponse> getPromotionById(@PathVariable Long id) {
        return ResponseEntity.ok(promotionService.getPromotionDto(id));
    }
}
