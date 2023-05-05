package lab.space.vilki_palki.model.product_type;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record ProductTypeResponse (
        Long id,
        String name
){
}
