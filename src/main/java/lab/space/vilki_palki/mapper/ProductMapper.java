package lab.space.vilki_palki.mapper;

import lab.space.vilki_palki.entity.Product;
import lab.space.vilki_palki.model.product.ProductResponse;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public ProductResponse toSimpleDto(Product product) {
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
                .productsCategory(product.getProductsCategory())
                .productsType(product.getProductsType())
                .structures(product.getStructures())
                .build();
    }
}
