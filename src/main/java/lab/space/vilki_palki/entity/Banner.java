package lab.space.vilki_palki.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lab.space.vilki_palki.entity.common.MappedEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "banners")
@Data
@EqualsAndHashCode(callSuper = true)
public class Banner extends MappedEntity {
    @Column(length = 150)
    private String image;
}
