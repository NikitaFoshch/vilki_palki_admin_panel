package lab.space.vilki_palki.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/structures")
@RequiredArgsConstructor
public class StructureController {
    @GetMapping({"/", ""})
    public String showStructurePage() {
        return "/admin-panel/pages/product-structure/structures";
    }
}
