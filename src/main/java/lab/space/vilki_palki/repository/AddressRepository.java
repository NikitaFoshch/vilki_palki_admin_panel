package lab.space.vilki_palki.repository;

import lab.space.vilki_palki.entity.Address;
import lab.space.vilki_palki.entity.Admin;
import lab.space.vilki_palki.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address,Long>, JpaSpecificationExecutor<Address> {
    List<Address> findAllByUserIdOrderByCreateAt(Long id);
}
