package lab.space.vilki_palki.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lab.space.vilki_palki.entity.StructureCategory;
import lab.space.vilki_palki.mapper.StructureCategoryMapper;
import lab.space.vilki_palki.model.structure_category.StructureCategoryResponse;
import lab.space.vilki_palki.repository.StructureCategoryRepository;
import lab.space.vilki_palki.service.StructureCategoryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class StructureCategoryServiceImpl implements StructureCategoryService {
    private final StructureCategoryRepository repository;

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
                .toList();
    }
}
