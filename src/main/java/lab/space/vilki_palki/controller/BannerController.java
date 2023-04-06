package lab.space.vilki_palki.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BannerController {
    @GetMapping({"/admin/banners/", "/admin/banners"})
    public String showBannerPage() {
        return "/admin-panel/pages/banner/banners";
    }
}
