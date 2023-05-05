package lab.space.vilki_palki.mapper;

import lab.space.vilki_palki.entity.Structure;
import lab.space.vilki_palki.model.structure.StructureResponse;
import lab.space.vilki_palki.service.StructureCategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class StructureMapper {
    private final StructureCategoryService structureCategoryService;

    public StructureResponse toDto(Structure structure) {
        return StructureResponse.builder()
                .id(structure.getId())
                .name(structure.getName())
                .structureCategory(structureCategoryService.getStructureCategoryToDto(structure.getStructureCategory().getId()))
                .weight(structure.getWeight().toString())
                .price(structure.getPrice())
                .image(structure.getImage())
                .build();
    }

    public StructureResponse toSimpleDto(Structure structure) {
        return StructureResponse.builder()
                .id(structure.getId())
                .name(structure.getName())
                .build();
    }
}
