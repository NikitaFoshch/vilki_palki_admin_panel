package lab.space.vilki_palki.controller;

import lab.space.vilki_palki.model.order.OrderRequest;
import lab.space.vilki_palki.model.order.OrderResponse;
import lab.space.vilki_palki.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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

    @PostMapping("get-active-orders-by-request")
    public ResponseEntity<Page<OrderResponse>> getActiveOrdersByRequest(@RequestBody OrderRequest orderRequest) {
        return ResponseEntity.ok(orderService.getActiveOrders(orderRequest));
    }

    @PostMapping("get-completed-orders-by-request")
    public ResponseEntity<Page<OrderResponse>> getCompletedOrdersByRequest(@RequestBody OrderRequest orderRequest) {
        return ResponseEntity.ok(orderService.getCompletedOrders(orderRequest));
    }

    @DeleteMapping("delete-order/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.ok().build();
    }

}
