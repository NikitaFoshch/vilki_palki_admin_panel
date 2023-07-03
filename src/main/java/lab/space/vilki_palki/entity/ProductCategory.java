package lab.space.vilki_palki.entity;

import javax.persistence.*;

import lab.space.vilki_palki.entity.common.MappedEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

@Entity
@Table(name = "products_categories")
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class ProductCategory extends MappedEntity {
    @Column(length = 100, nullable = false, unique = true)
    private String name;
    @Column(length = 150, nullable = false)
    private String image;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productCategory")
    private List<Product> product;
}
