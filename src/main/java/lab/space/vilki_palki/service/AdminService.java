package lab.space.vilki_palki.service;

import lab.space.vilki_palki.entity.Admin;
import lab.space.vilki_palki.entity.User;

import java.util.List;

public interface AdminService {
    Admin getAdminByEmail(String email);
    List<Admin> getAllAdmin();
    Admin getAdminById(Long id);
    void deleteUserById(Long id);
}
