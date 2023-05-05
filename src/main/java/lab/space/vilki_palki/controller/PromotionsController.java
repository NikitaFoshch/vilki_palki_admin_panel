package lab.space.vilki_palki.controller;

import jakarta.validation.Valid;
import lab.space.vilki_palki.model.product.ProductResponse;
import lab.space.vilki_palki.model.promotion.PromotionResponse;
import lab.space.vilki_palki.model.promotion.PromotionSaveRequest;
import lab.space.vilki_palki.model.promotion.PromotionUpdateRequest;
import lab.space.vilki_palki.service.ProductService;
import lab.space.vilki_palki.service.PromotionService;
import lab.space.vilki_palki.util.ErrorMapper;
import lab.space.vilki_palki.validator.ImageValidation;
import lab.space.vilki_palki.validator.PromotionValidation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("promotions")
@AllArgsConstructor
public class PromotionsController {

    private final PromotionService promotionService;
    private final ProductService productService;
    private final PromotionValidation promotionValidation;
    private final ImageValidation imageValidation;

    @GetMapping({"/", ""})
    public String showPromotionsPage() {
        return "/admin-panel/pages/promotions/promotions";
    }

    @PostMapping("promotion-save")
    @ResponseBody
    public ResponseEntity<?> savePromotion(@Valid @ModelAttribute PromotionSaveRequest request,
                                           BindingResult bindingResult) {
        promotionValidation.isNameUniqueValidation(request.name(), bindingResult);
        imageValidation.imageContentTypeValidation(request.image(), bindingResult);
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(ErrorMapper.mapErrors(bindingResult));
        }
        promotionService.savePromotion(request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("promotion-update")
    @ResponseBody
    public ResponseEntity<?> updatePromotion(@Valid @ModelAttribute PromotionUpdateRequest request,
                                             BindingResult bindingResult) {
        promotionValidation.isNameUniqueValidationWithId(request.id(), request.name(), bindingResult);
        imageValidation.imageContentTypeValidation(request.image(), bindingResult);
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(ErrorMapper.mapErrors(bindingResult));
        }
        promotionService.updatePromotionById(request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("promotion-delete/{id}")
    public ResponseEntity<?> deletePromotion(@PathVariable Long id) {
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


    @GetMapping("get-all-products-order-by-name")
    public ResponseEntity<List<ProductResponse>> getAllProduct() {
        return ResponseEntity.ok(productService.getAllProductsSimpleDtoByOrderByName());
    }
}
