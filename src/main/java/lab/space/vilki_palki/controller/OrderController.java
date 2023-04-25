package lab.space.vilki_palki.controller;

import lab.space.vilki_palki.model.OrderRequest;
import lab.space.vilki_palki.model.OrderResponse;
import lab.space.vilki_palki.model.OrderResponseByPage;
import lab.space.vilki_palki.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    @GetMapping({"/", ""})
    public String showOrderPage() {
        return "/admin-panel/pages/order/orders";
    }

    @PostMapping("get-active-orders")
    public ResponseEntity<OrderResponseByPage> getAllActiveOrders(@RequestBody OrderRequest orderRequest) {
        return ResponseEntity.ok(orderService.getOrdersByPage(orderRequest));
    }

    @PostMapping("get-complete-orders")
    public ResponseEntity<OrderResponseByPage> getAllCompleteOrders(@RequestBody OrderRequest orderRequest) {
        return ResponseEntity.ok(orderService.getOrdersByPage(orderRequest));
    }
}
