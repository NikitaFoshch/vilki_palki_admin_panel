package lab.space.vilki_palki.service;

import lab.space.vilki_palki.entity.StructureCategory;
import lab.space.vilki_palki.model.structure_category.StructureCategoryRequest;
import lab.space.vilki_palki.model.structure_category.StructureCategoryResponse;
import lab.space.vilki_palki.model.structure_category.StructureCategorySaveRequest;
import lab.space.vilki_palki.model.structure_category.StructureCategoryUpdateRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface StructureCategoryService {
    StructureCategory getStructureCategoryById(Long id);

    StructureCategoryResponse getStructureCategoryToDto(Long id);

    List<StructureCategoryResponse> getAllStructureCategories();

    Page<StructureCategoryResponse> getAllStructureCategoriesByOrderByCreateAt(StructureCategoryRequest request);

    void saveStructureCategory(StructureCategorySaveRequest request);

    void updateStructureCategory(StructureCategoryUpdateRequest request);

    void deleteStructureCategoryById(Long id);

}
