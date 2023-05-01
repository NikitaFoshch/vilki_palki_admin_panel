package lab.space.vilki_palki.controller;

import lab.space.vilki_palki.model.order.OrderRequest;
import lab.space.vilki_palki.model.order.OrderResponseByPage;
import lab.space.vilki_palki.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("orders")
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

    @DeleteMapping("delete-order/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.ok().build();
    }

}
