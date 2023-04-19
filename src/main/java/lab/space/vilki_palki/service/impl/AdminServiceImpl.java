package lab.space.vilki_palki.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lab.space.vilki_palki.entity.Admin;
import lab.space.vilki_palki.entity.User;
import lab.space.vilki_palki.mapper.AdminMapper;
import lab.space.vilki_palki.model.AdminResponse;
import lab.space.vilki_palki.repository.AdminRepository;
import lab.space.vilki_palki.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminServiceImpl implements AdminService, UserDetailsService {
    private final AdminRepository adminRepository;
    private final AdminMapper adminMapper;

    @Override
    public Admin getAdminByEmail(String email) {
        log.info("---------------Get Admin By email" + email + "---------------");
        return adminRepository.findByEmail(email)
                .orElseThrow(()-> new EntityNotFoundException("Not found admin with email " + email));
    }

    @Override
    public List<AdminResponse> getAllAdmins() {
        log.info("---------------Get All Admins Order By createAt---------------");
        return adminMapper
                .toListDto(adminRepository
                        .findAll(Sort.by(Sort.Direction.DESC,"createAt")));
    }

    @Override
    public Admin getAdminById(Long id) {
        log.info("---------------Get Admin By Id" + id + "---------------");
        return adminRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Not found admin with id " + id));
    }

    @Override
    public void deleteAdminById(Long id) {
        log.info("---------------Delete Admin By Id" + id + "---------------");
        Admin admin = getAdminById(id);
        adminRepository.delete(admin);
        log.info("---------------Success delete Admin By Id" + id + "---------------");
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserDetails userDetails = getAdminByEmail(email);
        return new org.springframework.security.core.userdetails.User(
                userDetails.getUsername(), userDetails.getPassword(), new ArrayList<>()
        );
    }

}
