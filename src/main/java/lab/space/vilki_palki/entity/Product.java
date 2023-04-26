package lab.space.vilki_palki.entity;

import jakarta.persistence.*;
import lab.space.vilki_palki.entity.common.MappedEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Entity
@Table(name = "products")
@Data
@EqualsAndHashCode(callSuper = true)
public class Product extends MappedEntity {
    @Column(length = 100)
    private String name;
    @Column(length = 500)
    private String productInfo;
    @Column(length = 2000)
    private String description;
    @Column(length = 150)
    private String image;
    @OneToOne
    private ProductsCategory productsCategory;
    @OneToOne
    private ProductsType productsType;
    @ManyToMany
    private List<Structure> structures;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "product_id")
    private List<ShoppingCart> shoppingCarts;
}
