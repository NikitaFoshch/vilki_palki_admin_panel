package lab.space.vilki_palki.model.banner;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class BannerResponse{
    private Long id;
    private String name;
    private String image;
}
