package lab.space.vilki_palki.validator;

import lab.space.vilki_palki.repository.PromotionRepository;
import lab.space.vilki_palki.service.PromotionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@Component
@AllArgsConstructor
public class PromotionValidation {
    private final PromotionRepository promotionRepository;
    private final PromotionService promotionService;

    public void isNameUniqueValidation(String name, BindingResult bindingResult) {
        if (promotionRepository.existsByName(name)) {
            bindingResult.addError(new FieldError("PromotionSaveRequest", "name", "Such name already exists"));
        }
    }

    public void isNameUniqueValidationWithId(Long id, String name, BindingResult bindingResult) {
        if (promotionRepository.existsByName(name) && !promotionService.getPromotionById(id).getName().equalsIgnoreCase(name)) {
            bindingResult.addError(new FieldError("PromotionUpdateRequest", "name", "Such name already exists"));
        }
    }
}
