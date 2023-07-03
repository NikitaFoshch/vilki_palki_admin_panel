package lab.space.vilki_palki.controller;

import lab.space.vilki_palki.model.admin.AdminRequest;
import lab.space.vilki_palki.model.admin.AdminResponseByPage;
import lab.space.vilki_palki.service.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("admins")
@AllArgsConstructor
public class StaffController {

    private final AdminService adminService;

    @GetMapping({"/", ""})
    public String showUserPage(Model model) {
        model.addAttribute("adminsCount", adminService.getCountByAllAdmins());
        return "/admin-panel/pages/admin/admins";
    }

    @PostMapping("get-all-admins")
    public ResponseEntity<AdminResponseByPage> getAllAdmins(@RequestBody AdminRequest adminRequest) {
        return ResponseEntity.ok(adminService.getAdminsResponseByPage(adminRequest));
    }

    @DeleteMapping("delete-admin/{id}")
    public ResponseEntity<?> deleteAdmin(@PathVariable Long id) {
        adminService.deleteAdminById(id);
        return ResponseEntity.ok().build();
    }

}
