package lab.space.vilki_palki.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import lab.space.vilki_palki.entity.common.MappedEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "shopping_carts")
@Data
@EqualsAndHashCode(callSuper = true)
public class ShoppingCart extends MappedEntity {
    private int count;
}
