package lab.space.vilki_palki.model.order;

import lombok.Data;

@Data
public class OrderPageRequest {
    private Integer pageIndex;
    private String query;
}
