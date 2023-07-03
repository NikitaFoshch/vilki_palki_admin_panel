package lab.space.vilki_palki.entity;

import javax.persistence.*;
import lab.space.vilki_palki.entity.common.MappedEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "products")
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Product extends MappedEntity {
    @Column(length = 100, nullable = false, unique = true)
    private String name;
    @Column(nullable = false)
    private BigDecimal price;
    @Column(length = 500, nullable = false)
    private String productInfo;
    @Column(length = 2000, nullable = false)
    private String description;
    @Column(length = 150, nullable = false)
    private String image;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "product_category_id", nullable = false)
    private ProductCategory productCategory;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "product_type_id", nullable = false)
    private ProductType productType;
    @ManyToMany(cascade = CascadeType.REMOVE)
    @JoinTable(
            name = "products_strucutres",
            joinColumns = @JoinColumn(name = "products_id"),
            inverseJoinColumns = @JoinColumn(name = "structures_id")
    )
    private List<Structure> structures;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "product_id")
    private List<ShoppingCart> shoppingCarts;
}
