package lab.space.vilki_palki.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminPanelController {
    @GetMapping({"/", ""})
    public String redirectAdmin() {
        return "redirect:/admin/statistics";
    }
    @GetMapping({"/admin/", "/admin"})
    public String redirectAdminPage() {
        return "redirect:/admin/statistics";
    }
}
