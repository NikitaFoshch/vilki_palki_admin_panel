package lab.space.vilki_palki.controller;

import lab.space.vilki_palki.entity.User;
import lab.space.vilki_palki.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @GetMapping({"/", ""})
    public String showUserPage(Model model) {
        model.addAttribute("users", userService.getAllUser());
        return "/admin-panel/pages/user/users";
    }
}
