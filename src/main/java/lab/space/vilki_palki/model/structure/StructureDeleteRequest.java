package lab.space.vilki_palki.model.structure;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record StructureDeleteRequest(Long id){}
