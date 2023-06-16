package lab.space.vilki_palki.service.impl;

import javax.persistence.EntityNotFoundException;
import lab.space.vilki_palki.entity.Structure;
import lab.space.vilki_palki.mapper.StructureMapper;
import lab.space.vilki_palki.model.structure.StructureRequest;
import lab.space.vilki_palki.model.structure.StructureResponse;
import lab.space.vilki_palki.model.structure.StructureSaveRequest;
import lab.space.vilki_palki.model.structure.StructureUpdateRequest;
import lab.space.vilki_palki.repository.StructureRepository;
import lab.space.vilki_palki.service.StructureCategoryService;
import lab.space.vilki_palki.service.StructureService;
import lab.space.vilki_palki.util.FileUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static java.util.Objects.nonNull;

@Service
@AllArgsConstructor
@Slf4j
public class StructureServiceImpl implements StructureService {
    private final StructureRepository structureRepository;
    private final StructureSpecification structureSpecification;
    private final StructureMapper structureMapper;
    private final StructureCategoryService structureCategoryService;

    @Override
    public StructureResponse getStructureDtoById(Long id) {
        return structureMapper.toDto(getById(id));
    }

    @Override
    public StructureResponse getStructureSimpleDtoById(Long id) {
        return structureMapper.toSimpleDto(getById(id));
    }

    @Override
    public Structure getById(Long id) {
        return structureRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Structure by id not found"));
    }

    @Override
    public Page<StructureResponse> getAllStructuresByOrderByCreateAt(StructureRequest structureRequest) {
        final int DEFAULT_PAGE_SIZE = 10;
        return structureRepository.findAll(structureSpecification.getStructuresByRequest(structureRequest),
                PageRequest.of(structureRequest.getPageIndex(), DEFAULT_PAGE_SIZE)).map(structureMapper::toDto);
    }

    @Override
    public List<StructureResponse> getAllProductStructuresByOrderByName() {
        return structureRepository.findAll(Sort.by(Sort.Direction.ASC,"name"))
                .stream()
                .map(structureMapper::toSimpleDto)
                .toList();
    }

    @Override
    public void saveStructure(StructureSaveRequest request) {
        Structure structure = new Structure()
                .setName(request.name())
                .setStructureCategory(structureCategoryService.getStructureCategoryById(request.structureCategoryId()))
                .setWeight(request.weight())
                .setPrice(request.price());
        final String newFileName = UUID.randomUUID() + request.image().getOriginalFilename();
        FileUtil.saveFile(newFileName, request.image());
        structure.setImage(newFileName);
        structureRepository.save(structure);
    }

    @Override
    public void updateStructure(StructureUpdateRequest request) {

        Structure structure = getById(request.id())
                .setName(request.name())
                .setStructureCategory(structureCategoryService.getStructureCategoryById(request.structureCategoryId()))
                .setWeight(request.weight())
                .setPrice(request.price());
            final String newFileName = UUID.randomUUID() + request.image().getOriginalFilename();
            FileUtil.saveFile(newFileName, request.image());
            FileUtil.deleteFile(structure.getImage());
            structure.setImage(newFileName);
        structureRepository.save(structure);
    }

    @Override
    public void deleteStructureById(Long id) {
        Structure structure = getById(id);
        if (nonNull(structure.getImage())) FileUtil.deleteFile(structure.getImage());

        structureRepository.delete(structure);
    }

}
