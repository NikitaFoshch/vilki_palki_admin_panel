package lab.space.vilki_palki.validator;

import lab.space.vilki_palki.entity.StructureCategory;
import lab.space.vilki_palki.repository.StructureCategoryRepository;
import lab.space.vilki_palki.service.StructureCategoryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StructureCategoryValidationTest {

    @Mock
    private StructureCategoryRepository structureCategoryRepository;
    @Mock
    private StructureCategoryService structureCategoryService;
    @InjectMocks
    private StructureCategoryValidation structureCategoryValidation;

    @Test
    void isNameUniqueValidationUniqueNameNoError() {
        String name = "UniqueName";
        BindingResult bindingResult = new BeanPropertyBindingResult(null, null);

        when(structureCategoryRepository.existsByName(name)).thenReturn(false);

        structureCategoryValidation.isNameUniqueValidation(name, bindingResult);

        assertEquals(0, bindingResult.getErrorCount());
    }

    @Test
    void isNameUniqueValidationDuplicateNameErrorAdded() {
        String name = "DuplicateName";
        BindingResult bindingResult = new BeanPropertyBindingResult(null, null);

        when(structureCategoryRepository.existsByName(name)).thenReturn(true);

        structureCategoryValidation.isNameUniqueValidation(name, bindingResult);

        assertEquals(1, bindingResult.getErrorCount());
        FieldError error = bindingResult.getFieldError("name");
        assertEquals("Such name already exists", error.getDefaultMessage());
    }

    @Test
    void isNameUniqueValidationWithIdUniqueNameNoError() {
        Long id = 1L;
        String name = "UniqueName";
        BindingResult bindingResult = new BeanPropertyBindingResult(null, null);

        when(structureCategoryRepository.existsByName(name)).thenReturn(false);

        structureCategoryValidation.isNameUniqueValidationWithId(id, name, bindingResult);

        assertEquals(0, bindingResult.getErrorCount());
    }

    @Test
    void isNameUniqueValidationWithIdDuplicateNameErrorAdded() {
        Long id = 1L;
        String name = "DuplicateName";
        BindingResult bindingResult = new BeanPropertyBindingResult(null, null);

        when(structureCategoryRepository.existsByName(name)).thenReturn(true);
        when(structureCategoryService.getStructureCategoryById(id)).thenReturn(new StructureCategory().setName("AnotherName"));

        structureCategoryValidation.isNameUniqueValidationWithId(id, name, bindingResult);

        assertEquals(1, bindingResult.getErrorCount());
        FieldError error = bindingResult.getFieldError("name");
        assertEquals("Such name already exists", error.getDefaultMessage());
    }
}