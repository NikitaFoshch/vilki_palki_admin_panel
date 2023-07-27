package lab.space.vilki_palki.entity;

import jakarta.persistence.*;

import lab.space.vilki_palki.entity.common.MappedEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

@Entity
@Table(name = "products_type")
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class ProductType extends MappedEntity {
    @Column(length = 100,nullable = false)
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productType")
    private List<Product> product;
}