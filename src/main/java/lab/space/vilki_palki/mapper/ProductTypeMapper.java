package lab.space.vilki_palki.mapper;

import lab.space.vilki_palki.entity.ProductType;
import lab.space.vilki_palki.model.product_type.ProductTypeResponse;

public interface ProductTypeMapper {
    static ProductTypeResponse toDto(ProductType productType) {
        return ProductTypeResponse.builder()
                .id(productType.getId())
                .name(productType.getName())
                .build();
    }
}
