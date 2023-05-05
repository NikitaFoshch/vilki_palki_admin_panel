package lab.space.vilki_palki.service;

import lab.space.vilki_palki.entity.ProductType;
import lab.space.vilki_palki.model.product_type.ProductTypeResponse;

import java.util.List;

public interface ProductTypeService {
    ProductType getProductTypeById(Long id);

    ProductTypeResponse getProductTypeToDto(Long id);

    List<ProductTypeResponse> getAllProductTypes();
}
