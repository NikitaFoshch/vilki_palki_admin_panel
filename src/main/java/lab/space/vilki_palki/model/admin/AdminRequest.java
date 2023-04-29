package lab.space.vilki_palki.model.admin;

import lombok.Data;

@Data
public class AdminRequest {
    private Integer pageIndex;
    private String query;
}
