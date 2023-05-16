package lab.space.vilki_palki.validator;

import lab.space.vilki_palki.repository.StructureCategoryRepository;
import lab.space.vilki_palki.service.StructureCategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
@Component
@AllArgsConstructor
public class StructureCategoryValidation {

    private final StructureCategoryRepository structureCategoryRepository;
    private final StructureCategoryService structureCategoryService;

    public void isNameUniqueValidation(String name, BindingResult bindingResult) {
        if (structureCategoryRepository.existsByName(name)) {
            bindingResult.addError(new FieldError("StructureSaveRequest", "name", "Such name already exists"));
        }
    }

    public void isNameUniqueValidationWithId(Long id, String name, BindingResult bindingResult) {
        if (structureCategoryRepository.existsByName(name) && !structureCategoryService.getStructureCategoryById(id).getName().equalsIgnoreCase(name)) {
            bindingResult.addError(new FieldError("StructureUpdateRequest", "name", "Such name already exists"));
        }
    }
}
