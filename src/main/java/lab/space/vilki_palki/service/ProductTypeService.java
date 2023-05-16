package lab.space.vilki_palki.service;

import lab.space.vilki_palki.entity.ProductType;
import lab.space.vilki_palki.model.product_type.ProductTypeRequest;
import lab.space.vilki_palki.model.product_type.ProductTypeResponse;
import lab.space.vilki_palki.model.product_type.ProductTypeSaveRequest;
import lab.space.vilki_palki.model.product_type.ProductTypeUpdateRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductTypeService {
    ProductType getProductTypeById(Long id);

    ProductTypeResponse getProductTypeToDto(Long id);

    List<ProductTypeResponse> getAllProductTypes();

    Page<ProductTypeResponse> getAllProductTypesByOrderByCreateAt(ProductTypeRequest request);

    void saveProductType(ProductTypeSaveRequest request);

    void updateProductType(ProductTypeUpdateRequest request);

    void deleteProductTypeById(Long id);
}
