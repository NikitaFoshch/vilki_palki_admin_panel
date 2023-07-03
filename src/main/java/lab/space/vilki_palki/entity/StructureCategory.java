package lab.space.vilki_palki.entity;

import javax.persistence.*;

import lab.space.vilki_palki.entity.common.MappedEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;


@Entity
@Table(name = "structure_categories")
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class StructureCategory extends MappedEntity {
    @Column(length = 20, nullable = false, unique = true)
    private String name;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Structure> structure;
}
