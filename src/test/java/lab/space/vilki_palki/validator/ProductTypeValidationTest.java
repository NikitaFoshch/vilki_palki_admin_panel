package lab.space.vilki_palki.validator;

import lab.space.vilki_palki.entity.ProductType;
import lab.space.vilki_palki.repository.ProductTypeRepository;
import lab.space.vilki_palki.service.ProductTypeService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductTypeValidationTest {

    @Mock
    private ProductTypeRepository productTypeRepository;
    @Mock
    private ProductTypeService productTypeService;
    @InjectMocks
    private ProductTypeValidation productTypeValidation;

    @Test
    void isNameUniqueValidationUniqueNameNoError() {
        String name = "UniqueName";
        BindingResult bindingResult = new BeanPropertyBindingResult(null, null);

        when(productTypeRepository.existsByName(name)).thenReturn(false);

        productTypeValidation.isNameUniqueValidation(name, bindingResult);

        assertEquals(0, bindingResult.getErrorCount());
    }

    @Test
    void isNameUniqueValidationDuplicateNameErrorAdded() {
        String name = "DuplicateName";
        BindingResult bindingResult = new BeanPropertyBindingResult(null, null);

        when(productTypeRepository.existsByName(name)).thenReturn(true);

        productTypeValidation.isNameUniqueValidation(name, bindingResult);

        assertEquals(1, bindingResult.getErrorCount());
        FieldError error = bindingResult.getFieldError("name");
        assertEquals("Such name already exists", error.getDefaultMessage());
    }

    @Test
    void isNameUniqueValidationWithIdUniqueNameNoError() {
        Long id = 1L;
        String name = "UniqueName";
        BindingResult bindingResult = new BeanPropertyBindingResult(null, null);

        when(productTypeRepository.existsByName(name)).thenReturn(false);
        when(productTypeService.getProductTypeById(id)).thenReturn(new ProductType().setName("AnotherName"));

        productTypeValidation.isNameUniqueValidationWithId(id, name, bindingResult);

        assertEquals(0, bindingResult.getErrorCount());
    }

    @Test
    void isNameUniqueValidationWithIdDuplicateNameErrorAdded() {
        Long id = 1L;
        String name = "DuplicateName";
        BindingResult bindingResult = new BeanPropertyBindingResult(null, null);

        when(productTypeRepository.existsByName(name)).thenReturn(true);
        when(productTypeService.getProductTypeById(id)).thenReturn(new ProductType().setName("AnotherName"));

        productTypeValidation.isNameUniqueValidationWithId(id, name, bindingResult);

        assertEquals(1, bindingResult.getErrorCount());
        FieldError error = bindingResult.getFieldError("name");
        assertEquals("Such name already exists", error.getDefaultMessage());
    }
}