package lab.space.vilki_palki.repository;

import lab.space.vilki_palki.entity.Address;
import lab.space.vilki_palki.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address,Long> {
    List<Address> findAllByUserIdOrderByCreateAt(Long id);
}
