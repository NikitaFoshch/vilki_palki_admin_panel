package lab.space.vilki_palki.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/banners")
@RequiredArgsConstructor
public class BannerController {
    @GetMapping({"/", ""})
    public String showBannerPage() {
        return "/admin-panel/pages/banner/banners";
    }
}
