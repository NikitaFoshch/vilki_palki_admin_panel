package lab.space.vilki_palki.service.impl;

import javax.persistence.EntityNotFoundException;
import lab.space.vilki_palki.entity.StructureCategory;
import lab.space.vilki_palki.mapper.StructureCategoryMapper;
import lab.space.vilki_palki.model.structure_category.StructureCategoryRequest;
import lab.space.vilki_palki.model.structure_category.StructureCategoryResponse;
import lab.space.vilki_palki.model.structure_category.StructureCategorySaveRequest;
import lab.space.vilki_palki.model.structure_category.StructureCategoryUpdateRequest;
import lab.space.vilki_palki.repository.StructureCategoryRepository;
import lab.space.vilki_palki.service.StructureCategoryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class StructureCategoryServiceImpl implements StructureCategoryService {
    private final StructureCategoryRepository repository;
    private final StructureCategorySpecification structureCategorySpecification;

    @Override

    public StructureCategory getStructureCategoryById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("StructureCategory not found by id " + id));
    }

    @Override
    public StructureCategoryResponse getStructureCategoryToDto(Long id) {
        return StructureCategoryMapper.toDto(getStructureCategoryById(id));
    }

    @Override
    public List<StructureCategoryResponse> getAllStructureCategories() {
        return repository.findAll(Sort.by(Sort.Direction.ASC, "name"))
                .stream()
                .map(StructureCategoryMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<StructureCategoryResponse> getAllStructureCategoriesByOrderByCreateAt(StructureCategoryRequest request) {
        final int DEFAULT_PAGE_SIZE = 10;
        return repository.findAll(structureCategorySpecification.getStructureCategoriesByRequest(request),
                PageRequest.of(request.getPageIndex(), DEFAULT_PAGE_SIZE)).map(StructureCategoryMapper::toDto);
    }

    @Override
    public void saveStructureCategory(StructureCategorySaveRequest request) {
        StructureCategory structureCategory = new StructureCategory()
                .setName(request.getName());
        repository.save(structureCategory);
    }

    @Override
    public void updateStructureCategory(StructureCategoryUpdateRequest request) {
        StructureCategory structureCategory = getStructureCategoryById(request.getId())
                .setName(request.getName());
        repository.save(structureCategory);
    }

    @Override
    public void deleteStructureCategoryById(Long id) {
        repository.delete(getStructureCategoryById(id));
    }
}
