package lab.space.vilki_palki.controller;

import lab.space.vilki_palki.model.banner.BannerResponse;
import lab.space.vilki_palki.model.banner.BannerSaveRequest;
import lab.space.vilki_palki.model.banner.BannerUpdateRequest;
import lab.space.vilki_palki.service.BannerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("banners")
@RequiredArgsConstructor
public class BannerController {
    private final BannerService bannerService;

    @GetMapping({"/", ""})
    public String showBannerPage() {
        return "/admin-panel/pages/banner/banners";
    }

    @PostMapping("banner-save")
    public ResponseEntity<?> saveBanner(@ModelAttribute BannerSaveRequest request) {
        bannerService.saveBanner(request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("banner-update")
    public ResponseEntity<?> updateBanner(@ModelAttribute BannerUpdateRequest request) {
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
