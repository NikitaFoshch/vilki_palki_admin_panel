package lab.space.vilki_palki.service;

import lab.space.vilki_palki.entity.Structure;
import lab.space.vilki_palki.model.structure.StructureRequest;
import lab.space.vilki_palki.model.structure.StructureResponse;
import lab.space.vilki_palki.model.structure.StructureSaveRequest;
import lab.space.vilki_palki.model.structure.StructureUpdateRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface StructureService {

    StructureResponse getStructureDtoById(Long id);
    StructureResponse getStructureSimpleDtoById(Long id);

    Structure getById(Long id);

    Page<StructureResponse> getAllStructuresByOrderByCreateAt(StructureRequest structureRequest);
    List<StructureResponse> getAllProductStructuresByOrderByName();

    void saveStructure(StructureSaveRequest request);

    void updateStructure(StructureUpdateRequest request);

    void deleteStructureById(Long id);

}
