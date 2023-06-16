package lab.space.vilki_palki.model.product_type;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ProductTypeResponse{
        private Long id;
        private String name;
}
