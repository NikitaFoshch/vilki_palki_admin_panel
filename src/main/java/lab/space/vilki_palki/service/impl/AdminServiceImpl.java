package lab.space.vilki_palki.service.impl;

import javax.persistence.EntityNotFoundException;
import lab.space.vilki_palki.entity.Admin;
import lab.space.vilki_palki.mapper.AdminMapper;
import lab.space.vilki_palki.model.admin.AdminRequest;
import lab.space.vilki_palki.model.admin.AdminResponseByPage;
import lab.space.vilki_palki.repository.AdminRepository;
import lab.space.vilki_palki.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminServiceImpl implements AdminService, UserDetailsService {
    private final AdminRepository adminRepository;
    private final AdminSpecification adminSpecification;

    @Override
    public Integer getCountByAllAdmins() {
        log.info("---------------Get Count By All Admins---------------");
        return adminRepository
                .findAll(Sort.by(Sort.Direction.DESC, "createAt")).size();
    }

    @Override
    public Admin getAdminByEmail(String email) {
        log.info("---------------Get Admin By email" + email + "---------------");
        return adminRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Not found admin with email " + email));
    }

    @Override
    public AdminResponseByPage getAdminsResponseByPage(AdminRequest adminRequest) {
        log.info("---------------Get Admins Order By createAt---------------");
        final int DEFAULT_PAGE_SIZE = 5;
        return AdminMapper.toAdminsResponseByPage(
                adminRepository.findAll(adminSpecification.getAdminsByRequest(adminRequest),
                        PageRequest.of(adminRequest.getPageIndex(),
                                DEFAULT_PAGE_SIZE,
                                Sort.by(Direction.DESC, "createAt"))));
    }

    @Override
    public Admin getAdminById(Long id) {
        log.info("---------------Get Admin By Id" + id + "---------------");
        return adminRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Not found admin with id " + id));
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
