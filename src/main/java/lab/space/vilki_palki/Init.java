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
            );
            log.info("Initial admin Created");
        }else log.info("Admin found");
    }
}
