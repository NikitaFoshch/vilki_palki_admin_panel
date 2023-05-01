package lab.space.vilki_palki.service;

import lab.space.vilki_palki.entity.Structure;
import lab.space.vilki_palki.model.structure.StructureRequest;
import lab.space.vilki_palki.model.structure.StructureResponse;
import lab.space.vilki_palki.model.structure.StructureSaveRequest;
import lab.space.vilki_palki.model.structure.StructureUpdateRequest;
import org.springframework.data.domain.Page;

public interface StructureService {

    StructureResponse getStructureById(Long id);

    Structure getById(Long id);

    Page<StructureResponse> getAllProductStructuresByOrderByCreateAt(StructureRequest structureRequest);

    void saveStructure(StructureSaveRequest request);

    void updateStructure(StructureUpdateRequest request);

    void deleteStructureById(Long id);
}
