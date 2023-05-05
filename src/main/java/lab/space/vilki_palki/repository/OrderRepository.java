package lab.space.vilki_palki.repository;

import lab.space.vilki_palki.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>, JpaSpecificationExecutor<Order> {
    List<Order> findAllByUserIdOrderByCreateAt(Long id);
    @Query("FROM Order o WHERE o.deliveryStatus IN ('ACCEPT', 'ON_WAY', 'IN_PROCESS')")
    Page<Order> findAllByActiveDeliveryStatus(Specification<Order> specification, Pageable pageable);

    @Query("FROM Order o WHERE o.deliveryStatus IN ('DONE', 'CANCELED')")
    Page<Order> findAllByCompletedDeliveryStatus(Specification<Order> specification, Pageable pageable);
}
