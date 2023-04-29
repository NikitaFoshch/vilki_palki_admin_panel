package lab.space.vilki_palki.model.address;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AddressResponse {

    private Long id;
    private String street;
    private String numberHouse;
    private String apartment;
    private String frontDoor;
    private String doorCode;
    private Integer floor;
    private String notes;

}
