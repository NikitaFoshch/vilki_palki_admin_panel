package lab.space.vilki_palki.model.product;

import com.fasterxml.jackson.annotation.JsonInclude;
import lab.space.vilki_palki.model.product_category.ProductCategoryResponse;
import lab.space.vilki_palki.model.product_type.ProductTypeResponse;
import lab.space.vilki_palki.model.structure.StructureResponse;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ProductResponse {
    private Long id;
    private String name;
    private BigDecimal price;
    private String productInfo;
    private String description;
    private String image;
    private ProductCategoryResponse productCategory;
    private ProductTypeResponse productType;
    private List<StructureResponse> structures;
}
