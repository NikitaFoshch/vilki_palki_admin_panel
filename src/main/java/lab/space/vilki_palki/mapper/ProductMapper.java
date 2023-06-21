package lab.space.vilki_palki.mapper;

import lab.space.vilki_palki.entity.Product;
import lab.space.vilki_palki.model.product.ProductResponse;
import lab.space.vilki_palki.service.ProductCategoryService;
import lab.space.vilki_palki.service.ProductTypeService;
import lab.space.vilki_palki.service.StructureService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ProductMapper {
    private final StructureService structureService;
    private final ProductTypeService productTypeService;
    private final ProductCategoryService productCategoryService;

    public ProductResponse toSimpleDto(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .build();
    }

    public ProductResponse toSimpleDtoWithImage(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .image(product.getImage())
                .build();
    }

    public ProductResponse toDto(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .productInfo(product.getProductInfo())
                .description(product.getDescription())
                .image(product.getImage())
                .price(product.getPrice())
                .productCategory(productCategoryService.getProductCategoryToSimpleDto(product.getProductCategory().getId()))
                .productType(productTypeService.getProductTypeToDto(product.getProductType().getId()))
                .structures(
                        product.getStructures()
                                .stream()
                                .map(structure -> structureService.getStructureSimpleDtoById(structure.getId())).collect(Collectors.toList())
                )
                .build();
    }
}
