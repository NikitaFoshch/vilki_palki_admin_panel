package lab.space.vilki_palki.model;

import lombok.Data;

@Data
public class OrderPageRequest {
    private Integer pageIndex;
    private String query;
}
