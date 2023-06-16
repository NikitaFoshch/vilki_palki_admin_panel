package lab.space.vilki_palki.service.impl;

import lab.space.vilki_palki.entity.Structure;
import lab.space.vilki_palki.entity.StructureCategory;
import lab.space.vilki_palki.mapper.StructureMapper;
import lab.space.vilki_palki.model.structure.StructureRequest;
import lab.space.vilki_palki.model.structure.StructureResponse;
import lab.space.vilki_palki.model.structure.StructureSaveRequest;
import lab.space.vilki_palki.model.structure.StructureUpdateRequest;
import lab.space.vilki_palki.repository.StructureRepository;
import lab.space.vilki_palki.service.StructureCategoryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.mock.web.MockMultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StructureServiceImplTest {

    @Mock
    private StructureRepository structureRepository;
    @Mock
    private StructureSpecification structureSpecification;
    @Mock
    private StructureMapper structureMapper;
    @Mock
    private StructureCategoryService structureCategoryService;

    @InjectMocks
    private StructureServiceImpl structureService;

    @Test
    void getStructureDtoById() {
        Long id = 1L;
        Structure structure = new Structure().setName("Bober").setImage("bober");
        structure.setId(1L);
        StructureResponse expectedResponse = new StructureResponse(1L, "Bober", null, null, null, "bober");
        when(structureRepository.findById(id)).thenReturn(Optional.of(structure));
        when(structureMapper.toDto(structure)).thenReturn(expectedResponse);

        StructureResponse response = structureService.getStructureDtoById(structure.getId());

        assertEquals(expectedResponse, response);
    }

    @Test
    void getStructureSimpleDtoById() {
        Long id = 1L;
        Structure structure = new Structure().setName("Bober").setImage("bober");
        structure.setId(1L);
        StructureResponse expectedResponse = new StructureResponse(1L, "Bober", null, null, null, "bober");
        when(structureRepository.findById(id)).thenReturn(Optional.of(structure));
        when(structureMapper.toSimpleDto(structure)).thenReturn(expectedResponse);

        StructureResponse response = structureService.getStructureSimpleDtoById(structure.getId());

        assertEquals(expectedResponse, response);
    }

    @Test
    void getById() {
        Long structureId = 1L;
        Structure structure = new Structure();
        structure.setId(structureId);

        when(structureRepository.findById(structureId)).thenReturn(Optional.of(structure));

        Structure structure1 = structureService.getById(structureId);
        assertEquals(structure, structure1);
        verify(structureRepository, times(1)).findById(structureId);
    }

    @Test
    void getAllStructuresByOrderByCreateAt() {
        int pageIndex = 1;
        String query = "";
        StructureRequest request = new StructureRequest();
        request.setPageIndex(pageIndex);
        request.setQuery(query);

        List<Structure> structures = new ArrayList<>();
        structures.add(new Structure());
        structures.add(new Structure());
        structures.add(new Structure());
        Page<Structure> structurePage = new PageImpl<>(structures);

        when(structureRepository.findAll((Specification<Structure>) any(), any(PageRequest.class))).thenReturn(structurePage);

        Page<StructureResponse> responseByPage = structureService.getAllStructuresByOrderByCreateAt(request);

        assertNotNull(responseByPage);
        assertEquals(structures.size(), responseByPage.getTotalElements());
        verify(structureRepository, times(1)).findAll((Specification<Structure>) any(), any(PageRequest.class));

    }

    @Test
    void getAllProductStructuresByOrderByName() {
        List<Structure> structures = new ArrayList<>();
        structures.add(new Structure());
        structures.add(new Structure());
        structures.add(new Structure());
        List<StructureResponse> responses = List.of(
                new StructureResponse(1L, "123", null, null, null, null),
                new StructureResponse(2L, "5435", null, null, null, null),
                new StructureResponse(3L, "33", null, null, null, null)
        );

        when(structureRepository.findAll(Sort.by(Sort.Direction.ASC, "name"))).thenReturn(structures);
        when(structureMapper.toSimpleDto(structures.get(0))).thenReturn(responses.get(0));
        when(structureMapper.toSimpleDto(structures.get(1))).thenReturn(responses.get(1));
        when(structureMapper.toSimpleDto(structures.get(2))).thenReturn(responses.get(2));

        List<StructureResponse> response = structureService.getAllProductStructuresByOrderByName();

        assertNotNull(response);
        assertEquals(structures.size(), response.size());
        verify(structureRepository, times(1)).findAll(Sort.by(Sort.Direction.ASC, "name"));
    }

    @Test
    void saveStructure() throws Exception {
        ClassPathResource resource = new ClassPathResource("img/img.png");
        MockMultipartFile image = new MockMultipartFile("image", "image", "imag/png", resource.getInputStream());
        StructureSaveRequest request = new StructureSaveRequest("Name", 1L, null, null, image);
        StructureCategory structureCategory = new StructureCategory();
        when(structureCategoryService.getStructureCategoryById(request.structureCategoryId())).thenReturn(structureCategory);

        structureService.saveStructure(request);

        verify(structureRepository, times(1)).save(any(Structure.class));
    }

    @Test
    void updateStructure() throws Exception {
        ClassPathResource resource = new ClassPathResource("img/img.png");
        MockMultipartFile image = new MockMultipartFile("image", "image", "imag/png", resource.getInputStream());
        StructureUpdateRequest request = new StructureUpdateRequest(1L, "Name", 1L, null, null, image);
        Structure structure = new Structure();
        structure.setId(1L);
        StructureCategory structureCategory = new StructureCategory();
        when(structureCategoryService.getStructureCategoryById(request.structureCategoryId())).thenReturn(structureCategory);
        when(structureRepository.findById(request.id())).thenReturn(Optional.of(structure));

        structureService.updateStructure(request);

        verify(structureRepository, times(1)).save(any(Structure.class));
    }

    @Test
    void deleteStructureById() {
        Long structureId = 1L;
        Structure structure = new Structure();
        structure.setId(structureId);

        when(structureRepository.findById(structureId)).thenReturn(Optional.of(structure));

        structureService.deleteStructureById(structureId);

        verify(structureRepository, times(1)).findById(structureId);
        verify(structureRepository, times(1)).delete(structure);
    }
}