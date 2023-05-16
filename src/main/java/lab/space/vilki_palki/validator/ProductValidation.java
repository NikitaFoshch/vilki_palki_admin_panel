package lab.space.vilki_palki.validator;

import lab.space.vilki_palki.repository.ProductRepository;
import lab.space.vilki_palki.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

@Component
@AllArgsConstructor
public class ProductValidation {
    private final ProductRepository productRepository;
    private final ProductService productService;

    public void isNameUniqueValidation(String name, BindingResult bindingResult) {
        if (productRepository.existsByName(name)) {
            bindingResult.addError(new FieldError("ProductSaveRequest", "name", "Such name already exists"));
        }
    }

    public void isNameUniqueValidationWithId(Long id, String name, BindingResult bindingResult) {
        if (productRepository.existsByName(name) && !productService.getProduct(id).getName().equalsIgnoreCase(name)) {
            bindingResult.addError(new FieldError("ProductUpdateRequest", "name", "Such name already exists"));
        }
    }

    public void isArrayValidation(List<Long> numbers, BindingResult bindingResult) {
        if (numbers.size() == 0) {
            bindingResult.addError(new FieldError("ProductUpdateRequest", "structureIds", "Must be specified"));
        }
    }
}
