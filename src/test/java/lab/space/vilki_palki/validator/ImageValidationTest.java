package lab.space.vilki_palki.validator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.multipart.MultipartFile;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class ImageValidationTest {

    @InjectMocks
    private ImageValidation imageValidation;

    @Test
    void imageContentTypeValidationValidImageNoErrorAdded() {
        MultipartFile file = new MockMultipartFile("image.jpg", "image.jpg", "image/jpeg", new byte[0]);
        BindingResult bindingResult = new BeanPropertyBindingResult(null, null);

        imageValidation.imageContentTypeValidation(file, bindingResult);

        assertEquals(0, bindingResult.getErrorCount());
    }

    @Test
    void imageContentTypeValidationInvalidImageErrorAdded() {
        MultipartFile file = new MockMultipartFile("image.pdf", "image.pdf", "image/pdf", new byte[0]);
        BindingResult bindingResult = new BeanPropertyBindingResult(null, null);

        imageValidation.imageContentTypeValidation(file, bindingResult);

        assertEquals(1, bindingResult.getErrorCount());
        FieldError error = bindingResult.getFieldError("image");
        assertEquals("Choose image", error.getDefaultMessage());
    }

    @Test
    void imageContentTypeValidationNoImageContentTypeNoErrorAdded() {
        MultipartFile file = new MockMultipartFile("document.pdf", "document.pdf", null, new byte[0]);
        BindingResult bindingResult = new BeanPropertyBindingResult(null, null);

        imageValidation.imageContentTypeValidation(file, bindingResult);

        assertEquals(0, bindingResult.getErrorCount());
    }

}