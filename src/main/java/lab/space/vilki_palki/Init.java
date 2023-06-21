package lab.space.vilki_palki;

import lab.space.vilki_palki.entity.Admin;
import lab.space.vilki_palki.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class Init implements CommandLineRunner {

    private final AdminRepository adminRepository;
    @Override
    public void run(String... args) throws Exception {
        log.info("Try to find admin");
        if (adminRepository.findAll().isEmpty()){
            log.warn("Create custom admin");
            adminRepository.save(
                    new Admin()
                            .setEmail("bob@gmail.com")
                            .setPassword("$2a$12$75IrwcjJ19FF4XxE7P2mROGRME9LFsRax.muaQN7IafZ1m4/dcknO")
                            .setFirstname("Bob")
                            .setLastname("Bobito")
            );
            adminRepository.save(
                    new Admin()
                            .setEmail("admin@gmail.com")
                            .setPassword("$2a$12$75IrwcjJ19FF4XxE7P2mROGRME9LFsRax.muaQN7IafZ1m4/dcknO")
                            .setFirstname("Admin")
                            .setLastname("Adminto")
            );
            adminRepository.save(
                    new Admin()
                            .setEmail("admin1@gmail.com")
                            .setPassword("$2a$12$75IrwcjJ19FF4XxE7P2mROGRME9LFsRax.muaQN7IafZ1m4/dcknO")
                            .setFirstname("Admin1")
                            .setLastname("Adminto1")
            );
            adminRepository.save(
                    new Admin()
                            .setEmail("admin2@gmail.com")
                            .setPassword("$2a$12$75IrwcjJ19FF4XxE7P2mROGRME9LFsRax.muaQN7IafZ1m4/dcknO")
                            .setFirstname("Admin2")
                            .setLastname("Adminto2")
            );
            log.info("Initial admin Created");
        }else log.info("Admin found");
    }
}
