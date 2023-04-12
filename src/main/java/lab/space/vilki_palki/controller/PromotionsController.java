package lab.space.vilki_palki.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/promotions")
@RequiredArgsConstructor
public class PromotionsController {
    @GetMapping({"/", ""})
    public String showPromotionsPage() {
        return "/admin-panel/pages/promotions/promotions";
    }
}
