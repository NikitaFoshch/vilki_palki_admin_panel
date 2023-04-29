package lab.space.vilki_palki.entity;

import jakarta.persistence.*;
import lab.space.vilki_palki.entity.common.MappedEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

@Entity
@Table(name = "promotions")
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Promotion extends MappedEntity {
    @Column(length = 50)
    private String name;
    private int percent;
    @Column(length = 150)
    private String image;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "promotions_id")
    private List<Product> products;
}
