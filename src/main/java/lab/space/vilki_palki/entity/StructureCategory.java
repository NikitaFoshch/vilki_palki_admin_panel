package lab.space.vilki_palki.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lab.space.vilki_palki.entity.common.MappedEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Entity
@Table(name = "structure_categories")
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class StructureCategory extends MappedEntity {
    @Column(length = 20, nullable = false, unique = true)
    private String name;
}
