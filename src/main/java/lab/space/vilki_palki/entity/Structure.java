package lab.space.vilki_palki.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lab.space.vilki_palki.entity.common.MappedEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Entity
@Table(name = "structures")
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Structure extends MappedEntity {

    @Column(length = 100)
    private String name;
    private int weight;
    private int price;
    @Column(length = 150)
    private String image;
    @OneToOne
    private StructureCategory structureCategory;
}

