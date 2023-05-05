package lab.space.vilki_palki.service;

import lab.space.vilki_palki.entity.ProductCategory;
import lab.space.vilki_palki.model.product_category.ProductCategoryResponse;

import java.util.List;

public interface ProductCategoryService {
    ProductCategory getProductCategoryById(Long id);

    ProductCategoryResponse getProductCategoryToDto(Long id);

    List<ProductCategoryResponse> getAllProductCategories();
}
