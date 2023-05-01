package lab.space.vilki_palki.mapper;

import lab.space.vilki_palki.entity.Admin;
import lab.space.vilki_palki.model.admin.AdminResponse;
import lab.space.vilki_palki.model.admin.AdminResponseByPage;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class AdminMapper {
    public AdminResponseByPage toAdminsResponseByPage(Page<Admin> admins) {
        return AdminResponseByPage.builder()
                .data(admins.stream().map(this::toDto).toList())
                .pagesCount(admins.getTotalPages())
                .itemsCount(admins.getTotalElements())
                .build();
    }

    public AdminResponse toDto(Admin admin) {
        return AdminResponse.builder()
                .id(admin.getId())
                .email(admin.getEmail())
                .firstname(admin.getFirstname())
                .lastname(admin.getLastname())
                .securityLevel(admin.getSecurityLevel())
                .build();
    }
}
