package lab.space.vilki_palki.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    @GetMapping({"/admin/users/", "/admin/users"})
    public String showUserPage() {
        return "/admin-panel/pages/user/users";
    }
}
