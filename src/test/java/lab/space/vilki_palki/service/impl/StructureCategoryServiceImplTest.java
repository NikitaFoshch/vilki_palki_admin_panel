package lab.space.vilki_palki.service.impl;

import lab.space.vilki_palki.entity.StructureCategory;
import lab.space.vilki_palki.mapper.StructureCategoryMapper;
import lab.space.vilki_palki.model.structure_category.StructureCategoryRequest;
import lab.space.vilki_palki.model.structure_category.StructureCategoryResponse;
import lab.space.vilki_palki.model.structure_category.StructureCategorySaveRequest;
import lab.space.vilki_palki.model.structure_category.StructureCategoryUpdateRequest;
import lab.space.vilki_palki.repository.StructureCategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StructureCategoryServiceImplTest {

    @Mock
    private StructureCategoryRepository repository;
    @Mock
    private StructureCategorySpecification structureCategorySpecification;
    @Mock
    private StructureCategoryMapper structureCategoryMapper;

    @InjectMocks
    private StructureCategoryServiceImpl structureCategoryService;


    @Test
    void getStructureCategoryById() {
        Long structureCategoryId = 1L;
        StructureCategory structureCategory = new StructureCategory();
        structureCategory.setId(structureCategoryId);

        when(repository.findById(structureCategoryId)).thenReturn(Optional.of(structureCategory));

        StructureCategory structureCategory1 = structureCategoryService.getStructureCategoryById(structureCategoryId);
        assertEquals(structureCategory, structureCategory1);
        verify(repository, times(1)).findById(structureCategoryId);
    }

    @Test
    void getStructureCategoryToDto() {
        Long structureCategoryId = 1L;
        StructureCategory structureCategory = new StructureCategory().setName("Bober");
        structureCategory.setId(1L);
        StructureCategoryResponse expectedResponse = new StructureCategoryResponse(1L, "Bober");

        when(repository.findById(structureCategoryId)).thenReturn(Optional.of(structureCategory));


        StructureCategoryResponse response = structureCategoryService.getStructureCategoryToDto(structureCategoryId);

        assertEquals(expectedResponse, response);
    }

    @Test
    void getAllStructureCategories() {
        List<StructureCategory> structureCategories = List.of(new StructureCategory(), new StructureCategory());
        structureCategories.get(0).setId(1L);
        structureCategories.get(0).setName("123");
        structureCategories.get(1).setId(2L);
        structureCategories.get(1).setName("5435");
        List<StructureCategoryResponse> responses = List.of(
                new StructureCategoryResponse(1L, "123"),
                new StructureCategoryResponse(2L, "5435")
        );

        when(repository.findAll(Sort.by(Sort.Direction.ASC, "name"))).thenReturn(structureCategories);
        List<StructureCategoryResponse> actualResponses = structureCategoryService.getAllStructureCategories();

        assertEquals(responses, actualResponses);
    }

    @Test
    void getAllStructureCategoriesByOrderByCreateAt() {
        int pageIndex = 1;
        String query = "";
        StructureCategoryRequest request = new StructureCategoryRequest();
        request.setPageIndex(pageIndex);
        request.setQuery(query);

        List<StructureCategory> orders = new ArrayList<>();
        orders.add(new StructureCategory());
        orders.add(new StructureCategory());
        orders.add(new StructureCategory());
        Page<StructureCategory> structureCategoryPage = new PageImpl<>(orders);

        when(repository.findAll((Specification<StructureCategory>) any(), any(PageRequest.class))).thenReturn(structureCategoryPage);

        Page<StructureCategoryResponse> responseByPage = structureCategoryService.getAllStructureCategoriesByOrderByCreateAt(request);

        assertNotNull(responseByPage);
        assertEquals(orders.size(), responseByPage.getTotalElements());
        verify(repository, times(1)).findAll((Specification<StructureCategory>) any(), any(PageRequest.class));
    }

    @Test
    void saveStructureCategory() {
        StructureCategorySaveRequest request = new StructureCategorySaveRequest("Name");

        structureCategoryService.saveStructureCategory(request);

        verify(repository, times(1)).save(any(StructureCategory.class));
    }

    @Test
    void updateStructureCategory() {
        StructureCategoryUpdateRequest request = new StructureCategoryUpdateRequest(1L, "Name");
        StructureCategory structureCategory = new StructureCategory().setName("name");
        structureCategory.setId(1L);

        when(repository.findById(request.id())).thenReturn(Optional.of(structureCategory));

        structureCategoryService.updateStructureCategory(request);

        verify(repository, times(1)).save(any(StructureCategory.class));
    }

    @Test
    void deleteStructureCategoryById() {
        Long structureCategoryId = 1L;
        StructureCategory structureCategory = new StructureCategory();
        structureCategory.setId(structureCategoryId);

        when(repository.findById(structureCategoryId)).thenReturn(Optional.of(structureCategory));

        structureCategoryService.deleteStructureCategoryById(structureCategoryId);

        verify(repository, times(1)).findById(structureCategoryId);
        verify(repository, times(1)).delete(structureCategory);
    }
}