package lab.space.vilki_palki.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lab.space.vilki_palki.entity.common.MappedEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Entity
@Table(name = "banners")
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Banner extends MappedEntity {
    @Column(length = 50, nullable = false)
    private String name;
    @Column(length = 150, nullable = false)
    private String image;
}
