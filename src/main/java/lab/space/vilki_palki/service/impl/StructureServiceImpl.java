package lab.space.vilki_palki.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lab.space.vilki_palki.entity.Structure;
import lab.space.vilki_palki.entity.StructureCategory;
import lab.space.vilki_palki.mapper.StructureMapper;
import lab.space.vilki_palki.model.StructureRequest;
import lab.space.vilki_palki.model.StructureResponse;
import lab.space.vilki_palki.model.StructureSaveRequest;
import lab.space.vilki_palki.model.StructureUpdateRequest;
import lab.space.vilki_palki.repository.StructureRepository;
import lab.space.vilki_palki.service.StructureService;
import lab.space.vilki_palki.util.FileUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
@Slf4j
public class StructureServiceImpl implements StructureService {
    private final StructureRepository structureRepository;
    private final StructureSpecification structureSpecification;
    private final StructureMapper structureMapper;

    @Override
    public StructureResponse getStructureById(Long id) {
        return structureMapper.toDto(getById(id));
    }

    @Override
    public Structure getById(Long id) {
        return structureRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Structure by id not found"));
    }

    @Override
    public Page<StructureResponse> getAllProductStructuresByOrderByCreateAt(StructureRequest structureRequest) {
        final int DEFAULT_PAGE_SIZE = 10;
        return structureRepository.findAll(structureSpecification.getStructuresByRequest(structureRequest),
                PageRequest.of(structureRequest.getPageIndex(), DEFAULT_PAGE_SIZE)).map(structureMapper::toDto);
    }

    @Override
    public void saveStructure(StructureSaveRequest request) {

        Structure structure = new Structure()
                .setName(request.title())
                .setStructureCategory(null)
                .setWeight(request.weight())
                .setPrice(request.price());

        if (nonNull(request.image())
                && nonNull(request.image().getOriginalFilename())
                && !request.image().getOriginalFilename().equals("")) {
            final String newFileName = UUID.randomUUID() + request.image().getOriginalFilename();
            FileUtil.saveFile(newFileName, request.image());
            structure.setImage(newFileName);
        }
        structureRepository.save(structure);
    }

    @Override
    public void updateStructure(StructureUpdateRequest request) {

        Structure structure = getById(request.id())
                .setName(request.title())
                .setStructureCategory(request.structureCategory())
                .setWeight(request.weight())
                .setPrice(request.price());

        structure.setId(request.id());
        if (nonNull(request.image())
                && nonNull(request.image().getOriginalFilename())
                && !request.image().getOriginalFilename().equals("")) {
            final String newFileName = UUID.randomUUID() + request.image().getOriginalFilename();
            FileUtil.deleteFile(structure.getImage());
            structure.setImage(newFileName);

        }
        structureRepository.save(structure);
    }

    @Override
    public void deleteStructureById(Long id) {
        Structure structure = getById(id);
        if (nonNull(structure.getImage())) FileUtil.deleteFile(structure.getImage());

        structureRepository.delete(structure);
    }
}
