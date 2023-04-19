package lab.space.vilki_palki.service;

import lab.space.vilki_palki.entity.Admin;
import lab.space.vilki_palki.entity.User;
import lab.space.vilki_palki.model.AdminResponse;

import java.util.List;

public interface AdminService {
    Admin getAdminByEmail(String email);
    List<AdminResponse> getAllAdmins();
    Admin getAdminById(Long id);
    void deleteAdminById(Long id);
}
