package lab.space.vilki_palki.service;

import lab.space.vilki_palki.entity.StructureCategory;
import lab.space.vilki_palki.model.structure_category.StructureCategoryResponse;

import java.util.List;

public interface StructureCategoryService {
    StructureCategory getStructureCategoryById(Long id);

    StructureCategoryResponse getStructureCategoryToDto(Long id);

    List<StructureCategoryResponse> getAllStructureCategories();

}
