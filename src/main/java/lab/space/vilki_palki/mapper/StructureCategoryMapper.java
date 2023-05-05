package lab.space.vilki_palki.mapper;

import lab.space.vilki_palki.entity.StructureCategory;
import lab.space.vilki_palki.model.structure_category.StructureCategoryResponse;

public interface StructureCategoryMapper {
    static StructureCategoryResponse toDto(StructureCategory structureCategory) {
        return StructureCategoryResponse.builder()
                .id(structureCategory.getId())
                .name(structureCategory.getName())
                .build();
    }
}
