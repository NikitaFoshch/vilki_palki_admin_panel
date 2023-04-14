package lab.space.vilki_palki.controller;

import lab.space.vilki_palki.entity.Banner;
import lab.space.vilki_palki.service.BannerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("banners")
@RequiredArgsConstructor
public class BannerController {
    private final BannerService bannerService;
    @GetMapping({"/", ""})
    public String showBannerPage(Model model) {
        model.addAttribute("banners",bannerService.getAllBannersByOrderByCreateAt());
        return "/admin-panel/pages/banner/banners";
    }
    @PostMapping("banner-save")
    public String saveBanner(@RequestPart String title,
                             @RequestPart MultipartFile image){
        bannerService.saveBanner(title, image);
        return "redirect:/banners";
    }
    @PostMapping("banner-update")
    public String updateBanner(@ModelAttribute Banner banner,
                             @RequestPart MultipartFile image){
        bannerService.updateBannerById(banner.getId(),banner ,image);
        return "redirect:/banners";
    }
    @GetMapping("banner-delete/{id}")
    public String deleteBanner(@PathVariable Long id){
        bannerService.deleteBanner(id);
        return "redirect:/banners";
    }
}
