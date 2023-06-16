package lab.space.vilki_palki.controller;


import lab.space.vilki_palki.model.banner.BannerResponse;
import lab.space.vilki_palki.model.banner.BannerSaveRequest;
import lab.space.vilki_palki.model.banner.BannerUpdateRequest;
import lab.space.vilki_palki.service.BannerService;
import lab.space.vilki_palki.util.ErrorMapper;
import lab.space.vilki_palki.validator.BannerValidation;
import lab.space.vilki_palki.validator.ImageValidation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("banners")
@AllArgsConstructor
public class BannerController {
    private final BannerService bannerService;
    private final BannerValidation bannerValidation;
    private final ImageValidation imageValidation;

    @GetMapping({"/", ""})
    public String showBannerPage() {
        return "/admin-panel/pages/banner/banners";
    }

    @PostMapping("banner-save")
    @ResponseBody
    public ResponseEntity<?> saveBanner(@Valid @ModelAttribute BannerSaveRequest request,
                                        BindingResult bindingResult) {
        bannerValidation.isNameUniqueValidation(request.getName(), bindingResult);
        imageValidation.imageContentTypeValidation(request.getImage(), bindingResult);
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(ErrorMapper.mapErrors(bindingResult));
        }
        bannerService.saveBanner(request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("banner-update")
    @ResponseBody
    public ResponseEntity<?> updateBanner(@Valid @ModelAttribute BannerUpdateRequest request,
                                          BindingResult bindingResult) {
        bannerValidation.isNameUniqueValidationWithId(request.getId(), request.getName(), bindingResult);
        imageValidation.imageContentTypeValidation(request.getImage(), bindingResult);
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(ErrorMapper.mapErrors(bindingResult));
        }
        bannerService.updateBannerById(request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("banner-delete/{id}")
    public ResponseEntity<?> deleteBanner(@PathVariable Long id) {
        bannerService.deleteBanner(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("get-all-banners")
    public ResponseEntity<List<BannerResponse>> getAllBanners() {
        return ResponseEntity.ok(bannerService.getAllBannersByOrderByCreateAt());
    }

    @GetMapping("get-banner/{id}")
    public ResponseEntity<BannerResponse> getBannerById(@PathVariable Long id) {
        return ResponseEntity.ok(bannerService.getBannerDto(id));
    }
}
