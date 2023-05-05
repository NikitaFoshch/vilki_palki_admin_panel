package lab.space.vilki_palki.validator;

import lab.space.vilki_palki.repository.StructureRepository;
import lab.space.vilki_palki.service.StructureService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@Component
@AllArgsConstructor
public class StructureValidator {

    private final StructureRepository structureRepository;
    private final StructureService structureService;

    public void isNameUniqueValidation(String name, BindingResult bindingResult) {
        if (structureRepository.existsByName(name)) {
            bindingResult.addError(new FieldError("StructureSaveRequest", "name", "Such name already exists"));
        }
    }

    public void isNameUniqueValidationWithId(Long id, String name, BindingResult bindingResult) {
        if (structureRepository.existsByName(name) && !structureService.getById(id).getName().equalsIgnoreCase(name)) {
            bindingResult.addError(new FieldError("StructureUpdateRequest", "name", "Such name already exists"));
        }
    }

}
