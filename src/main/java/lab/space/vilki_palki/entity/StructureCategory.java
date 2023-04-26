package lab.space.vilki_palki.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lab.space.vilki_palki.entity.common.MappedEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "structure_categories")
@Data
@EqualsAndHashCode(callSuper = true)
public class StructureCategory extends MappedEntity {
    @Column(length = 20)
    private String name;
}
