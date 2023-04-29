package lab.space.vilki_palki.model.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lab.space.vilki_palki.entity.Address;
import lab.space.vilki_palki.entity.Order;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UserResponse {
    private Long id;
    private String email;
    private String name;
    @JsonFormat(pattern = "dd.MM.yyyy", timezone = "UTC")
    private Instant birthday;
    private String facebookId;
    private String phoneNumber;
    private Integer sumOrders;
    private List<Order> orders;
    private List<Address> addresses;
}
