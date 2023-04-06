package lab.space.vilki_palki.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StructureController {
    @GetMapping({"/admin/structures/", "/admin/structures"})
    public String showStructurePage() {
        return "/admin-panel/pages/product-structure/structures";
    }
}
