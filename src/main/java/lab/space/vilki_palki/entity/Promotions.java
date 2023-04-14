package lab.space.vilki_palki.entity;

import jakarta.persistence.*;
import lab.space.vilki_palki.entity.common.MappedEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Entity
@Table(name = "promotions")
@Data
@EqualsAndHashCode(callSuper = true)
public class Promotions extends MappedEntity {
    @Column(length = 50)
    private String name;
    private int percent;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "promotions_id")
    private List<Product> products;
}
