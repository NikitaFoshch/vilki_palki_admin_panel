package lab.space.vilki_palki.model.product_category;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ProductCategoryResponse{
        private Long id;
        private String name;
        private String image;
}
