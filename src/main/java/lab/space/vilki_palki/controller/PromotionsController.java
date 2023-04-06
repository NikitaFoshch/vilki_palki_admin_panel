package lab.space.vilki_palki.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PromotionsController {
    @GetMapping({"/admin/promotions/", "/admin/promotions"})
    public String showPromotionsPage() {
        return "/admin-panel/pages/promotions/promotions";
    }
}
