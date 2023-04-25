package lab.space.vilki_palki.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class AdminResponseByPage {
    private List<AdminResponse> data;
    private Long itemsCount;
    private Integer pagesCount;
}
