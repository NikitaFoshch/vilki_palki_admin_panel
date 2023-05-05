package lab.space.vilki_palki.mapper;

import lab.space.vilki_palki.entity.ProductCategory;
import lab.space.vilki_palki.model.product_category.ProductCategoryResponse;

public interface ProductCategoryMapper {
    static ProductCategoryResponse toDto(ProductCategory productCategory) {
        return ProductCategoryResponse.builder()
                .id(productCategory.getId())
                .name(productCategory.getName())
                .build();
    }
}
