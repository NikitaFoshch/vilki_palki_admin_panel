package lab.space.vilki_palki.mapper;

import lab.space.vilki_palki.entity.Structure;
import lab.space.vilki_palki.model.structure.StructureResponse;
import org.springframework.stereotype.Component;

@Component
public class StructureMapper {
    public StructureResponse toDto(Structure structure) {
        return StructureResponse.builder()
                .id(structure.getId())
                .name(structure.getName())
                .structureCategory(structure.getStructureCategory())
                .weight(structure.getWeight())
                .price(structure.getPrice())
                .image(structure.getImage())
                .build();
    }
}
