package lab.space.vilki_palki.validator;

import lab.space.vilki_palki.repository.BannerRepository;
import lab.space.vilki_palki.service.BannerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@Component
@AllArgsConstructor
public class BannerValidation {
    private final BannerRepository bannerRepository;
    private final BannerService bannerService;

    public void isNameUniqueValidation(String name, BindingResult bindingResult) {
        if (bannerRepository.existsByName(name)) {
            bindingResult.addError(new FieldError("BannerSaveRequest", "name", "Such name already exists"));
        }
    }

    public void isNameUniqueValidationWithId(Long id, String name, BindingResult bindingResult) {
        if (bannerRepository.existsByName(name) && !bannerService.getBannerById(id).getName().equalsIgnoreCase(name)) {
            bindingResult.addError(new FieldError("BannerUpdateRequest", "name", "Such name already exists"));
        }
    }
}
