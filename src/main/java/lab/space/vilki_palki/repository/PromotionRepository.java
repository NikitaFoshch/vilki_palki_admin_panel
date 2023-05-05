package lab.space.vilki_palki.repository;

import lab.space.vilki_palki.entity.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Long> {
    boolean existsByName(String name);
}
