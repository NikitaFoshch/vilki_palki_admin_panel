package lab.space.vilki_palki.service;

import lab.space.vilki_palki.entity.Structure;
import lab.space.vilki_palki.entity.StructureCategory;
import lab.space.vilki_palki.model.StructureRequest;
import lab.space.vilki_palki.model.StructureResponse;
import lab.space.vilki_palki.model.StructureSaveRequest;
import lab.space.vilki_palki.model.StructureUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

public interface StructureService {

    StructureResponse getStructureById(Long id);
    Structure getById(Long id);

    Page<StructureResponse> getAllProductStructuresByOrderByCreateAt(StructureRequest structureRequest);

    void saveStructure(StructureSaveRequest request);
    void updateStructure(StructureUpdateRequest request);

    void deleteStructureById(Long id);
}
