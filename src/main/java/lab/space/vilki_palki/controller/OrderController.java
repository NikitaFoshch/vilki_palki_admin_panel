package lab.space.vilki_palki.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderController {
    @GetMapping({"/admin/orders/", "/admin/orders"})
    public String showOrderPage() {
        return "/admin-panel/pages/order/orders";
    }
}
