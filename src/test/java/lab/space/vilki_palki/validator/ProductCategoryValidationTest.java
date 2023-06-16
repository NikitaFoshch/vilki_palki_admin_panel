package lab.space.vilki_palki.validator;

import lab.space.vilki_palki.entity.ProductCategory;
import lab.space.vilki_palki.repository.ProductCategoryRepository;
import lab.space.vilki_palki.service.ProductCategoryService;
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
class ProductCategoryValidationTest {

    @Mock
    private ProductCategoryRepository productCategoryRepository;
    @Mock
    private ProductCategoryService productCategoryService;
    @InjectMocks
    private ProductCategoryValidation productCategoryValidation;

    @Test
    void isNameUniqueValidationUniqueNameNoError() {
        String name = "UniqueName";
        BindingResult bindingResult = new BeanPropertyBindingResult(null, null);

        when(productCategoryRepository.existsByName(name)).thenReturn(false);

        productCategoryValidation.isNameUniqueValidation(name, bindingResult);

        assertEquals(0, bindingResult.getErrorCount());
    }

    @Test
    void isNameUniqueValidationDuplicateNameErrorAdded() {
        String name = "DuplicateName";
        BindingResult bindingResult = new BeanPropertyBindingResult(null, null);

        when(productCategoryRepository.existsByName(name)).thenReturn(true);

        productCategoryValidation.isNameUniqueValidation(name, bindingResult);

        assertEquals(1, bindingResult.getErrorCount());
        FieldError error = bindingResult.getFieldError("name");
        assertEquals("Such name already exists", error.getDefaultMessage());
    }

    @Test
    void isNameUniqueValidationWithIdUniqueNameNoError() {
        Long id = 1L;
        String name = "UniqueName";
        BindingResult bindingResult = new BeanPropertyBindingResult(null, null);

        when(productCategoryRepository.existsByName(name)).thenReturn(false);

        productCategoryValidation.isNameUniqueValidationWithId(id, name, bindingResult);

        assertEquals(0, bindingResult.getErrorCount());
    }

    @Test
    void isNameUniqueValidationWithIdDuplicateNameErrorAdded() {
        Long id = 1L;
        String name = "DuplicateName";
        BindingResult bindingResult = new BeanPropertyBindingResult(null, null);

        when(productCategoryRepository.existsByName(name)).thenReturn(true);
        when(productCategoryService.getProductCategoryById(id)).thenReturn(new ProductCategory().setName("AnotherName"));

        productCategoryValidation.isNameUniqueValidationWithId(id, name, bindingResult);

        assertEquals(1, bindingResult.getErrorCount());
        FieldError error = bindingResult.getFieldError("name");
        assertEquals("Such name already exists", error.getDefaultMessage());
    }
}