package lab.space.vilki_palki.validator;

import lab.space.vilki_palki.repository.ProductCategoryRepository;
import lab.space.vilki_palki.service.ProductCategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@Component
@AllArgsConstructor
public class ProductCategoryValidation {

    private final ProductCategoryRepository productCategoryRepository;
    private final ProductCategoryService productCategoryService;

    public void isNameUniqueValidation(String name, BindingResult bindingResult) {
        if (productCategoryRepository.existsByName(name)) {
            bindingResult.addError(new FieldError("ProductCategorySaveRequest", "name", "Such name already exists"));
        }
    }

    public void isNameUniqueValidationWithId(Long id, String name, BindingResult bindingResult) {
        if (productCategoryRepository.existsByName(name) && !productCategoryService.getProductCategoryById(id).getName().equalsIgnoreCase(name)) {
            bindingResult.addError(new FieldError("ProductCategoryUpdateRequest", "name", "Such name already exists"));
        }
    }
}
