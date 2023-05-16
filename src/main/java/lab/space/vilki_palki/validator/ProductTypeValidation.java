package lab.space.vilki_palki.validator;

import lab.space.vilki_palki.repository.ProductTypeRepository;
import lab.space.vilki_palki.service.ProductTypeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@Component
@AllArgsConstructor
public class ProductTypeValidation {
    private final ProductTypeRepository productTypeRepository;
    private final ProductTypeService productTypeService;

    public void isNameUniqueValidation(String name, BindingResult bindingResult) {
        if (productTypeRepository.existsByName(name)) {
            bindingResult.addError(new FieldError("ProductTypeSaveRequest", "name", "Such name already exists"));
        }
    }

    public void isNameUniqueValidationWithId(Long id, String name, BindingResult bindingResult) {
        if (productTypeRepository.existsByName(name) && !productTypeService.getProductTypeById(id).getName().equalsIgnoreCase(name)) {
            bindingResult.addError(new FieldError("ProductTypeUpdateRequest", "name", "Such name already exists"));
        }
    }
}
