package lab.space.vilki_palki.service;

import lab.space.vilki_palki.entity.Admin;
import lab.space.vilki_palki.model.AdminRequest;
import lab.space.vilki_palki.model.AdminResponseByPage;

public interface AdminService {
    Integer getCountByAllAdmins();
    Admin getAdminByEmail(String email);

    AdminResponseByPage getAdminsResponseByPage(AdminRequest adminRequest);

    Admin getAdminById(Long id);

    void deleteAdminById(Long id);
}
