package lab.space.vilki_palki.service;

import lab.space.vilki_palki.entity.Admin;
import lab.space.vilki_palki.model.admin.AdminRequest;
import lab.space.vilki_palki.model.admin.AdminResponseByPage;

public interface AdminService {
    Integer getCountByAllAdmins();
    Admin getAdminByEmail(String email);

    AdminResponseByPage getAdminsResponseByPage(AdminRequest adminRequest);

    Admin getAdminById(Long id);

    void deleteAdminById(Long id);
}
