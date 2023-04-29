package lab.space.vilki_palki.service;

import lab.space.vilki_palki.entity.Product;
import lab.space.vilki_palki.model.product.ProductResponse;
import lab.space.vilki_palki.model.product.ProductSaveRequest;
import lab.space.vilki_palki.model.product.ProductUpdateRequest;

import java.util.List;

public interface ProductService {
    List<ProductResponse> getAllProductsByOrderByCreateAt();

    Product getProduct(Long id);

    ProductResponse getProductDto(Long id);

    void saveProduct(ProductSaveRequest request);

    void updateProductById(ProductUpdateRequest request);

    void deleteProduct(Long id);
}
