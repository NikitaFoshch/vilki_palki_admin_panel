package lab.space.vilki_palki.controller;

import lab.space.vilki_palki.entity.User;
import lab.space.vilki_palki.model.address.AddressRequest;
import lab.space.vilki_palki.model.address.AddressResponseByPage;
import lab.space.vilki_palki.model.admin.AdminRequest;
import lab.space.vilki_palki.model.admin.AdminResponseByPage;
import lab.space.vilki_palki.model.order.OrderRequest;
import lab.space.vilki_palki.model.order.OrderResponseByPage;
import lab.space.vilki_palki.model.user.UserRequest;
import lab.space.vilki_palki.model.user.UserResponse;
import lab.space.vilki_palki.service.AddressService;
import lab.space.vilki_palki.service.AdminService;
import lab.space.vilki_palki.service.OrderService;
import lab.space.vilki_palki.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final AdminService adminService;
    private final OrderService orderService;
    private final AddressService addressService;

    @GetMapping({"/", ""})
    public String showUserPage(Model model) {
        model.addAttribute("usersCount", userService.getCountByAllUsers());
        model.addAttribute("adminsCount", adminService.getCountByAllAdmins());
        return "/admin-panel/pages/user/users";
    }

    @PostMapping("get-all-users")
    public ResponseEntity<Page<UserResponse>> getAllUsers(@RequestBody UserRequest userRequest) {
        return ResponseEntity.ok(userService.getUsersByPage(userRequest));
    }

    @PostMapping("get-all-admins")
    public ResponseEntity<AdminResponseByPage> getAllAdmins(@RequestBody AdminRequest adminRequest) {
        return ResponseEntity.ok(adminService.getAdminsResponseByPage(adminRequest));
    }

    @DeleteMapping("delete-user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("user-details/{id}")
    public String showUserDetailsPage(@PathVariable Long id, Model model) {
        User user = new User();
        user.setId(id);
        model.addAttribute("user", user);
        return "/admin-panel/pages/user/user-details";
    }

    @DeleteMapping("delete-admin/{id}")
    public ResponseEntity<?> deleteAdmin(@PathVariable Long id) {
        adminService.deleteAdminById(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("get-all-orders-by-user-id")
    public ResponseEntity<OrderResponseByPage> getAllOrdersByUserId(@RequestBody OrderRequest orderRequest) {
        return ResponseEntity.ok(orderService.getOrdersByPageByUserId(orderRequest));
    }

    @PostMapping("get-all-address-by-user-id")
    public ResponseEntity<AddressResponseByPage> getAllAddressByUserId(@RequestBody AddressRequest addressRequest) {
        return ResponseEntity.ok(addressService.getAddressesByPageByUserId(addressRequest));
    }

    @DeleteMapping("delete-order/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("delete-address/{id}")
    public ResponseEntity<?> deleteAddress(@PathVariable Long id) {
        addressService.deleteAddress(id);
        return ResponseEntity.ok().build();
    }

}
