package lab.space.vilki_palki.repository;

import lab.space.vilki_palki.entity.Structure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StructureRepository extends JpaRepository<Structure,Long> {
}
