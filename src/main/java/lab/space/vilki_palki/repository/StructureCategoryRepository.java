package lab.space.vilki_palki.repository;

import lab.space.vilki_palki.entity.StructureCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface StructureCategoryRepository extends JpaRepository<StructureCategory, Long>, JpaSpecificationExecutor<StructureCategory> {
    boolean existsByName(String name);
}
