package lab.space.vilki_palki.validator;

import lab.space.vilki_palki.entity.Product;
import lab.space.vilki_palki.repository.ProductRepository;
import lab.space.vilki_palki.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductValidationTest {
    @Mock
    private ProductRepository productRepository;
    @Mock
    private ProductService productService;
    @InjectMocks
    private ProductValidation productValidation;

    @Test
    void isNameUniqueValidationUniqueNameNoError() {
        String name = "UniqueName";
        BindingResult bindingResult = new BeanPropertyBindingResult(null, null);

        when(productRepository.existsByName(name)).thenReturn(false);

        productValidation.isNameUniqueValidation(name, bindingResult);

        assertEquals(0, bindingResult.getErrorCount());
    }

    @Test
    void isNameUniqueValidationDuplicateNameErrorAdded() {
        String name = "DuplicateName";
        BindingResult bindingResult = new BeanPropertyBindingResult(null, null);

        when(productRepository.existsByName(name)).thenReturn(true);

        productValidation.isNameUniqueValidation(name, bindingResult);

        assertEquals(1, bindingResult.getErrorCount());
        FieldError error = bindingResult.getFieldError("name");
        assertEquals("Such name already exists", error.getDefaultMessage());
    }

    @Test
    void isNameUniqueValidationWithIdUniqueNameNoError() {
        Long id = 1L;
        String name = "UniqueName";
        BindingResult bindingResult = new BeanPropertyBindingResult(null, null);

        when(productRepository.existsByName(name)).thenReturn(false);

        productValidation.isNameUniqueValidationWithId(id, name, bindingResult);

        assertEquals(0, bindingResult.getErrorCount());
    }

    @Test
    void isNameUniqueValidationWithIdDuplicateNameErrorAdded() {
        Long id = 1L;
        String name = "DuplicateName";
        BindingResult bindingResult = new BeanPropertyBindingResult(null, null);

        when(productRepository.existsByName(name)).thenReturn(true);
        when(productService.getProduct(id)).thenReturn(new Product().setName("AnotherName"));

        productValidation.isNameUniqueValidationWithId(id, name, bindingResult);

        assertEquals(1, bindingResult.getErrorCount());
        FieldError error = bindingResult.getFieldError("name");
        assertEquals("Such name already exists", error.getDefaultMessage());
    }

    @Test
    void isArrayValidationEmptyListErrorAdded() {
        List<Long> numbers = new ArrayList<>();
        BindingResult bindingResult = new BeanPropertyBindingResult(null, null);

        productValidation.isArrayValidation(numbers, bindingResult);

        assertEquals(1, bindingResult.getErrorCount());
        FieldError error = bindingResult.getFieldError("structureIds");
        assertEquals("Must be specified", error.getDefaultMessage());
    }

    @Test
    void isArrayValidationNonEmptyListNoErrorAdded() {
        List<Long> numbers = Arrays.asList(1L, 2L, 3L);
        BindingResult bindingResult = new BeanPropertyBindingResult(null, null);

        productValidation.isArrayValidation(numbers, bindingResult);

        assertEquals(0, bindingResult.getErrorCount());
    }
}