package lab.space.vilki_palki;

import lab.space.vilki_palki.entity.Admin;
import lab.space.vilki_palki.entity.Order;
import lab.space.vilki_palki.entity.User;
import lab.space.vilki_palki.repository.AdminRepository;
import lab.space.vilki_palki.repository.OrderRepository;
import lab.space.vilki_palki.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.Instant;

@Component
@RequiredArgsConstructor
@Slf4j
public class Init implements CommandLineRunner {

    private final AdminRepository adminRepository;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        log.info("Try to find admin");
        if (adminRepository.findAll().isEmpty()) {
            log.warn("Create custom admins");
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
            log.info("Initial admins Created");
        } else log.info("Admin found");
        log.info("Try to find user");
        if (userRepository.findAll().isEmpty()) {
            log.warn("Create custom users");
            userRepository.save(
                    new User()
                            .setEmail("bober____@gmail.com")
            );
            userRepository.save(
                    new User()
                            .setEmail("bober_777king777@gmail.com")
            );
            userRepository.save(
                    new User()
                            .setEmail("bober_wizard@gmail.com")
            );
            log.info("Initial users Created");
        } else log.info("User found");
        log.info("Try to find orders");
        if (orderRepository.findAll().isEmpty()) {
            log.warn("Create custom orders");
            for (int i = 0; i < 3; i++) {
                orderRepository.save(
                        new Order()
                                .setOrderCode(String.valueOf((Math.random() * (999999 - 100000))))
                                .setPrice(BigDecimal.valueOf((Math.random() * (9999 - 1000))))
                                .setDeliveryStatus(Order.DeliveryStatus.CANCELED)
                                .setDeliveryTime(Instant.now().minusSeconds(60 * 60 * 24 * 332))
                                .setCarStatus(true)
                                .setTimeStatus(false)
                                .setCommon_kit(10)
                                .setCardPay(true)
                                .setCashPay(false)
                                .setProducts("Burger(10),Coca-Cola(5L),Peperoni(2)")
                                .setAddress("Odessa, str.Koroleva 1, ap.15")
                                .setUser(userRepository.findUserByEmail("bober_777king777@gmail.com"))

                );
                orderRepository.save(
                        new Order()
                                .setOrderCode(String.valueOf((Math.random() * (999999 - 100000))))
                                .setPrice(BigDecimal.valueOf((Math.random() * (9999 - 1000))))
                                .setDeliveryStatus(Order.DeliveryStatus.DONE)
                                .setDeliveryTime(Instant.now().minusSeconds(60 * 60 * 24 * 300))
                                .setCarStatus(true)
                                .setTimeStatus(false)
                                .setCommon_kit(10)
                                .setCardPay(true)
                                .setCashPay(false)
                                .setProducts("Burger(107),Coca-Cola(8L),Peperoni(5)")
                                .setAddress("Odessa, str.Koroleva 16, ap.115")
                                .setUser(userRepository.findUserByEmail("bober_777king777@gmail.com"))

                );
                orderRepository.save(
                        new Order()
                                .setOrderCode(String.valueOf((Math.random() * (999999 - 100000))))
                                .setPrice(BigDecimal.valueOf((Math.random() * (9999 - 1000))))
                                .setDeliveryStatus(Order.DeliveryStatus.DONE)
                                .setDeliveryTime(Instant.now().minusSeconds(60 * 60 * 24 * 269))
                                .setCarStatus(true)
                                .setTimeStatus(false)
                                .setCommon_kit(10)
                                .setCardPay(true)
                                .setCashPay(false)
                                .setProducts("Burger(7),Coca-Cola(1L),Peperoni(1)")
                                .setAddress("Odessa, str.Kievskaya 160, ap.15")
                                .setUser(userRepository.findUserByEmail("bober_777king777@gmail.com"))

                );
                orderRepository.save(
                        new Order()
                                .setOrderCode(String.valueOf((Math.random() * (999999 - 100000))))
                                .setPrice(BigDecimal.valueOf((Math.random() * (9999 - 1000))))
                                .setDeliveryStatus(Order.DeliveryStatus.DONE)
                                .setDeliveryTime(Instant.now().minusSeconds(60 * 60 * 24 * 238))
                                .setCarStatus(true)
                                .setTimeStatus(false)
                                .setCommon_kit(10)
                                .setCardPay(true)
                                .setCashPay(false)
                                .setProducts("Burger(7),Coca-Cola(1L),Peperoni(1)")
                                .setAddress("Odessa, str.Kievskaya 1, ap.15")
                                .setUser(userRepository.findUserByEmail("bober_777king777@gmail.com"))

                );
                orderRepository.save(
                        new Order()
                                .setOrderCode(String.valueOf((Math.random() * (999999 - 100000))))
                                .setPrice(BigDecimal.valueOf((Math.random() * (9999 - 1000))))
                                .setDeliveryStatus(Order.DeliveryStatus.CANCELED)
                                .setDeliveryTime(Instant.now().minusSeconds(60 * 60 * 24 * 200))
                                .setCarStatus(true)
                                .setTimeStatus(false)
                                .setCommon_kit(10)
                                .setCardPay(true)
                                .setCashPay(false)
                                .setProducts("Burger(11),Coca-Cola(1L),Peperoni(1)")
                                .setAddress("Odessa, str.Kievskaya 1, ap.15")
                                .setUser(userRepository.findUserByEmail("bober_777king777@gmail.com"))

                );
                orderRepository.save(
                        new Order()
                                .setOrderCode(String.valueOf((Math.random() * (999999 - 100000))))
                                .setPrice(BigDecimal.valueOf((Math.random() * (9999 - 1000))))
                                .setDeliveryStatus(Order.DeliveryStatus.CANCELED)
                                .setDeliveryTime(Instant.now().minusSeconds(60 * 60 * 24 * 168))
                                .setCarStatus(true)
                                .setTimeStatus(false)
                                .setCommon_kit(10)
                                .setCardPay(true)
                                .setCashPay(false)
                                .setProducts("Burger(11),Coca-Cola(1L),Peperoni(1)")
                                .setAddress("Odessa, str.Kievskaya 1, ap.15")
                                .setUser(userRepository.findUserByEmail("bober____@gmail.com"))

                );
                orderRepository.save(
                        new Order()
                                .setOrderCode(String.valueOf((Math.random() * (999999 - 100000))))
                                .setPrice(BigDecimal.valueOf((Math.random() * (9999 - 1000))))
                                .setDeliveryStatus(Order.DeliveryStatus.CANCELED)
                                .setDeliveryTime(Instant.now().minusSeconds(60 * 60 * 24 * 135))
                                .setCarStatus(true)
                                .setTimeStatus(false)
                                .setCommon_kit(10)
                                .setCardPay(true)
                                .setCashPay(false)
                                .setProducts("Burger(11),Coca-Cola(1L),Peperoni(1)")
                                .setAddress("Odessa, str.Kievskaya 1, ap.15")
                                .setUser(userRepository.findUserByEmail("bober_777king777@gmail.com"))

                );
                orderRepository.save(
                        new Order()
                                .setOrderCode(String.valueOf((Math.random() * (999999 - 100000))))
                                .setPrice(BigDecimal.valueOf((Math.random() * (9999 - 1000))))
                                .setDeliveryStatus(Order.DeliveryStatus.CANCELED)
                                .setDeliveryTime(Instant.now().minusSeconds(60 * 60 * 24 * 100))
                                .setCarStatus(true)
                                .setTimeStatus(false)
                                .setCommon_kit(10)
                                .setCardPay(true)
                                .setCashPay(false)
                                .setProducts("Burger(24),Coca-Cola(4L),Peperoni(5)")
                                .setAddress("Odessa, str.Kievskaya 11, ap.15")
                                .setUser(userRepository.findUserByEmail("bober_wizard@gmail.com"))

                );
                orderRepository.save(
                        new Order()
                                .setOrderCode(String.valueOf((Math.random() * (999999 - 100000))))
                                .setPrice(BigDecimal.valueOf((Math.random() * (9999 - 1000))))
                                .setDeliveryStatus(Order.DeliveryStatus.DONE)
                                .setDeliveryTime(Instant.now().minusSeconds(60 * 60 * 24 * 70))
                                .setCarStatus(true)
                                .setTimeStatus(false)
                                .setCommon_kit(10)
                                .setCardPay(true)
                                .setCashPay(false)
                                .setProducts("Burger(24),Coca-Cola(4L),Peperoni(5)")
                                .setAddress("Odessa, str.Kievskaya 11, ap.15")
                                .setUser(userRepository.findUserByEmail("bober_wizard@gmail.com"))

                );
                orderRepository.save(
                        new Order()
                                .setOrderCode(String.valueOf((Math.random() * (999999 - 100000))))
                                .setPrice(BigDecimal.valueOf((Math.random() * (9999 - 1000))))
                                .setDeliveryStatus(Order.DeliveryStatus.CANCELED)
                                .setDeliveryTime(Instant.now().minusSeconds(60 * 60 * 24 * 35))
                                .setCarStatus(true)
                                .setTimeStatus(false)
                                .setCommon_kit(10)
                                .setCardPay(true)
                                .setCashPay(false)
                                .setProducts("Burger(24),Coca-Cola(4L),Peperoni(5)")
                                .setAddress("Odessa, str.Kievskaya 11, ap.15")
                                .setUser(userRepository.findUserByEmail("bober_wizard@gmail.com"))

                );
                orderRepository.save(
                        new Order()
                                .setOrderCode(String.valueOf((Math.random() * (999999 - 100000))))
                                .setPrice(BigDecimal.valueOf((Math.random() * (9999 - 1000))))
                                .setDeliveryStatus(Order.DeliveryStatus.DONE)
                                .setDeliveryTime(Instant.now().minusSeconds(60 * 60 * 24 * 2))
                                .setCarStatus(true)
                                .setTimeStatus(false)
                                .setCommon_kit(10)
                                .setCardPay(true)
                                .setCashPay(false)
                                .setProducts("Burger(24),Coca-Cola(4L),Peperoni(5)")
                                .setAddress("Odessa, str.Kievskaya 11, ap.15")
                                .setUser(userRepository.findUserByEmail("bober_wizard@gmail.com"))

                );
                orderRepository.save(
                        new Order()
                                .setOrderCode(String.valueOf((Math.random() * (999999 - 100000))))
                                .setPrice(BigDecimal.valueOf((Math.random() * (9999 - 1000))))
                                .setDeliveryStatus(Order.DeliveryStatus.ACCEPT)
                                .setDeliveryTime(Instant.now().plusSeconds(60 * 60))
                                .setCarStatus(true)
                                .setTimeStatus(false)
                                .setCommon_kit(10)
                                .setCardPay(true)
                                .setCashPay(false)
                                .setProducts("Burger(24),Coca-Cola(4L),Peperoni(5)")
                                .setAddress("Odessa, str.Kievskaya 11, ap.15")
                                .setUser(userRepository.findUserByEmail("bober_777king777@gmail.com"))

                );
                orderRepository.save(
                        new Order()
                                .setOrderCode(String.valueOf((Math.random() * (999999 - 100000))))
                                .setPrice(BigDecimal.valueOf((Math.random() * (9999 - 1000))))
                                .setDeliveryStatus(Order.DeliveryStatus.IN_PROCESS)
                                .setDeliveryTime(Instant.now().plusSeconds(60 * 40))
                                .setCarStatus(true)
                                .setTimeStatus(false)
                                .setCommon_kit(10)
                                .setCardPay(true)
                                .setCashPay(false)
                                .setProducts("Burger(24),Coca-Cola(4L),Peperoni(5)")
                                .setAddress("Odessa, str.Kievskaya 11, ap.15")
                                .setUser(userRepository.findUserByEmail("bober_777king777@gmail.com"))

                );
                orderRepository.save(
                        new Order()
                                .setOrderCode(String.valueOf((Math.random() * (999999 - 100000))))
                                .setPrice(BigDecimal.valueOf((Math.random() * (9999 - 1000))))
                                .setDeliveryStatus(Order.DeliveryStatus.ON_WAY)
                                .setDeliveryTime(Instant.now().plusSeconds(60 * 20))
                                .setCarStatus(true)
                                .setTimeStatus(false)
                                .setCommon_kit(10)
                                .setCardPay(true)
                                .setCashPay(false)
                                .setProducts("Burger(24),Coca-Cola(4L),Peperoni(5)")
                                .setAddress("Odessa, str.Kievskaya 11, ap.15")
                                .setUser(userRepository.findUserByEmail("bober_wizard@gmail.com"))

                );
            }
            log.info("Initial orders Created");
        } else log.info("Order found");

    }
}
