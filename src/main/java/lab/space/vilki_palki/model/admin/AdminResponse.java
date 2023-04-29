package lab.space.vilki_palki.model.admin;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AdminResponse {
    private Long id;
    private String email;
    private String firstname;
    private String lastname;
    private int securityLevel;
}
