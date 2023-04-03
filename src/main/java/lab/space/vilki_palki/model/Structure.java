package lab.space.vilki_palki.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lab.space.vilki_palki.model.common.MappedEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "structures")
@Data
@EqualsAndHashCode(callSuper = true)
public class Structure extends MappedEntity {

    @Column(length = 100)
    private String name;
    private int weight;
    private int price;
    @Column(length = 150)
    private String image;
    @OneToOne
    private Category category;
}

