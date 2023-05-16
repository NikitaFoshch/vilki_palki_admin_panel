package lab.space.vilki_palki.model.product_category;

import lombok.Data;

@Data
public class ProductCategoryRequest {
    private Integer pageIndex;
    private String query;
}
