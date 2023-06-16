package lab.space.vilki_palki.service.impl;

import lab.space.vilki_palki.entity.Admin;
import lab.space.vilki_palki.model.admin.AdminRequest;
import lab.space.vilki_palki.model.admin.AdminResponseByPage;
import lab.space.vilki_palki.repository.AdminRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AdminServiceImplTest {

    @Mock
    private AdminRepository adminRepository;
    @Mock
    private AdminSpecification adminSpecification;

    @InjectMocks
    private AdminServiceImpl adminService;

    @Test
    void getCountByAllAdmins() {
        List<Admin> admins = new ArrayList<>();
        admins.add(new Admin());
        admins.add(new Admin());
        admins.add(new Admin());

        when(adminRepository.findAll(Sort.by(Sort.Direction.DESC, "createAt"))).thenReturn(admins);

        int sizeAdminDB = adminService.getCountByAllAdmins();

        assertEquals(3, sizeAdminDB);
    }

    @Test
    void getAdminByEmail() {
        String email = "lil@gmail.com";
        Admin expectedAdmin = new Admin();
        expectedAdmin.setEmail(email);

        when(adminRepository.findByEmail(email)).thenReturn(Optional.of(expectedAdmin));

        Admin admin = adminService.getAdminByEmail(email);
        assertEquals(expectedAdmin, admin);
    }

    @Test
    void getAdminsResponseByPage() {

        int pageIndex = 1;
        String query = "";
        AdminRequest adminRequest = new AdminRequest();
        adminRequest.setPageIndex(pageIndex);
        adminRequest.setQuery(query);

        List<Admin> admins = new ArrayList<>();
        admins.add(new Admin());
        admins.add(new Admin());
        admins.add(new Admin());
        Page<Admin> adminPage = new PageImpl<>(admins);

        when(adminRepository.findAll((Specification<Admin>) any(), any(PageRequest.class))).thenReturn(adminPage);

        AdminResponseByPage responseByPage = adminService.getAdminsResponseByPage(adminRequest);

        assertNotNull(responseByPage);
        assertEquals(admins.size(), responseByPage.getData().size());
        verify(adminRepository, times(1)).findAll((Specification<Admin>) any(), any(PageRequest.class));

    }

    @Test
    void getAdminById() {
        Long id = 1L;
        Admin expectedAdmin = new Admin();
        expectedAdmin.setId(id);

        when(adminRepository.findById(id)).thenReturn(Optional.of(expectedAdmin));

        Admin admin = adminService.getAdminById(id);
        assertEquals(expectedAdmin, admin);
    }

    @Test
    void deleteAdminById() {
        Long id = 1L;
        Admin expectedAdmin = new Admin();
        expectedAdmin.setId(id);

        when(adminRepository.findById(id)).thenReturn(Optional.of(expectedAdmin));

        adminService.deleteAdminById(id);
    }

    @Test
    void loadUserByUsername() {
        String email = "lil@gmail.com";
        Admin expectedAdmin = new Admin();
        expectedAdmin.setEmail(email);
        expectedAdmin.setPassword("1234");

        when(adminRepository.findByEmail(email)).thenReturn(Optional.of(expectedAdmin));

        UserDetails admin = adminService.loadUserByUsername(email);
        System.out.println(admin);
        assertEquals(expectedAdmin.getEmail(), admin.getUsername());
    }
}