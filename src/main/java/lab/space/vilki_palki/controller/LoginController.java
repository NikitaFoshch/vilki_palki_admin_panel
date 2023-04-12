package lab.space.vilki_palki.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
    @GetMapping({"/admin/login/", "/admin/login"})
    public String showLogin() {
        return "/admin-panel/pages/login/login";
    }

}