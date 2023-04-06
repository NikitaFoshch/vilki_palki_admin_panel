package lab.space.vilki_palki.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductController {
    @GetMapping({"/admin/products/", "/admin/products"})
    public String showProductPage() {
        return "/admin-panel/pages/product-structure/products";
    }
}
