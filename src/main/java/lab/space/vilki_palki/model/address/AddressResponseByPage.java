package lab.space.vilki_palki.model.address;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class AddressResponseByPage {
    private List<AddressResponse> data;
    private Long itemsCount;
    private Integer pagesCount;
}