package lab.space.vilki_palki.mapper;

import lab.space.vilki_palki.entity.Admin;
import lab.space.vilki_palki.model.AddressResponse;
import lab.space.vilki_palki.model.AdminResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AdminMapper {
    public List<AdminResponse> toListDto(List<Admin> admins){
        return admins.stream().map(this::toDto).toList();
    }
    public AdminResponse toDto(Admin admin){
        return AdminResponse.builder()
                .id(admin.getId())
                .email(admin.getEmail())
                .firstname(admin.getFirstname())
                .lastname(admin.getLastname())
                .securityLevel(admin.getSecurityLevel())
                .build();
    }
}
