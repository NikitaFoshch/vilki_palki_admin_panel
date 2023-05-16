package lab.space.vilki_palki.service;

import lab.space.vilki_palki.entity.ProductCategory;
import lab.space.vilki_palki.model.product_category.ProductCategoryRequest;
import lab.space.vilki_palki.model.product_category.ProductCategoryResponse;
import lab.space.vilki_palki.model.product_category.ProductCategorySaveRequest;
import lab.space.vilki_palki.model.product_category.ProductCategoryUpdateRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductCategoryService {
    ProductCategory getProductCategoryById(Long id);

    ProductCategoryResponse getProductCategoryToDto(Long id);
    ProductCategoryResponse getProductCategoryToSimpleDto(Long id);

    List<ProductCategoryResponse> getAllProductCategories();

    Page<ProductCategoryResponse> getAllProductCategoriesByOrderByCreateAt(ProductCategoryRequest request);

    void saveProductCategory(ProductCategorySaveRequest request);

    void updateProductCategory(ProductCategoryUpdateRequest request);

    void deleteProductCategoryById(Long id);

}
