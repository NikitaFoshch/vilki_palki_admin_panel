package lab.space.vilki_palki.controller;

import lab.space.vilki_palki.entity.User;
import lab.space.vilki_palki.model.AddressResponse;
import lab.space.vilki_palki.model.AdminResponse;
import lab.space.vilki_palki.model.OrderResponse;
import lab.space.vilki_palki.model.UserResponse;
import lab.space.vilki_palki.service.AddressService;
import lab.space.vilki_palki.service.AdminService;
import lab.space.vilki_palki.service.OrderService;
import lab.space.vilki_palki.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final AdminService adminService;
    private final OrderService orderService;
    private final AddressService addressService;

    @GetMapping({"/", ""})
    public String showUserPage() {
        return "/admin-panel/pages/user/users";
    }
    @GetMapping("get-all-users")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }
    @GetMapping("get-all-admins")
    public ResponseEntity<List<AdminResponse>> getAllAdmins() {
        return ResponseEntity.ok(adminService.getAllAdmins());
    }

    @GetMapping("delete-user/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
        return "redirect:/users";
    }

    @GetMapping("user-details/{id}")
    public String showUserDetailsPage(@PathVariable Long id, Model model) {
        User user = new User();
        user.setId(id);
        model.addAttribute("user",user);
        return "/admin-panel/pages/user/user-details";
    }

    @GetMapping("delete-admin/{id}")
    public String deleteAdmin(@PathVariable Long id) {
        adminService.deleteAdminById(id);
        return "redirect:/users";
    }
    @GetMapping("get-all-orders-by-user-id")
    public ResponseEntity<List<OrderResponse>> getAllOrdersByUserId(@RequestParam("id") Long id) {
        return ResponseEntity.ok(orderService.findAllOrdersByUserIdByOrderByCreateAt(id));
    }
    @GetMapping("get-all-address-by-user-id")
    public ResponseEntity<List<AddressResponse>> getAllAddressByUserId(@RequestParam("id") Long id) {
        List<AddressResponse> addressResponses = addressService.findAllOrdersByUserIdByOrderByCreateAt(id);
        System.out.println(addressResponses);
        return ResponseEntity.ok(addressService.findAllOrdersByUserIdByOrderByCreateAt(id));
    }

}
