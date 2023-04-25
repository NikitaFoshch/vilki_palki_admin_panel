package lab.space.vilki_palki.model;

import lombok.Data;

@Data
public class OrderRequest {
    private Integer pageIndex;
    private String query;
    private Long userId;
}
