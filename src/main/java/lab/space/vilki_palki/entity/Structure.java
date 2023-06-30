package lab.space.vilki_palki.entity;

import javax.persistence.*;
import lab.space.vilki_palki.entity.common.MappedEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Entity
@Table(name = "structures")
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Structure extends MappedEntity {

    @Column(length = 100, unique = true, nullable = false)
    private String name;
    @Column(nullable = false)
    private Integer weight;
    @Column(nullable = false)
    private BigDecimal price;
    @Column(length = 150, nullable = false)
    private String image;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "structure_category_id", nullable = false)
    private StructureCategory structureCategory;
}

