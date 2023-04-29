package lab.space.vilki_palki.model.user;

import lombok.Data;

@Data
public class UserRequest {
    private Integer pageIndex;
    private String query;
}
