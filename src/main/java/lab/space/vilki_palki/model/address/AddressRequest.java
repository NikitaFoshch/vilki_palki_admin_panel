package lab.space.vilki_palki.model.address;

import lombok.Data;

@Data
public class AddressRequest {
    private Integer pageIndex;
    private String query;
    private Long userId;
}
