package lab.space.vilki_palki.service.impl;

import lab.space.vilki_palki.entity.ProductCategory;
import lab.space.vilki_palki.model.product_category.ProductCategoryRequest;
import lab.space.vilki_palki.model.product_category.ProductCategoryResponse;
import lab.space.vilki_palki.model.product_category.ProductCategorySaveRequest;
import lab.space.vilki_palki.model.product_category.ProductCategoryUpdateRequest;
import lab.space.vilki_palki.repository.ProductCategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
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
class ProductCategoryServiceImplTest {
    @Mock
    private ProductCategoryRepository productCategoryRepository;
    @Mock
    private ProductCategorySpecification productCategorySpecification;
    @InjectMocks
    private ProductCategoryServiceImpl productCategoryService;

    @Test
    void getProductCategoryById() {
        Long productCategoryId = 1L;
        ProductCategory productCategory = new ProductCategory();
        productCategory.setId(productCategoryId);

        when(productCategoryRepository.findById(productCategoryId)).thenReturn(Optional.of(productCategory));

        ProductCategory productCategory1 = productCategoryService.getProductCategoryById(productCategoryId);
        assertEquals(productCategory, productCategory1);
        verify(productCategoryRepository, times(1)).findById(productCategoryId);
    }

    @Test
    void getProductCategoryToDto() {
        Long productCategoryId = 1L;
        ProductCategory productCategory = new ProductCategory().setName("AFK").setImage("ALD");
        productCategory.setId(productCategoryId);
        ProductCategoryResponse expectedResponse = ProductCategoryResponse.builder().id(1L).name("AFK").image("ALD").build();
        when(productCategoryRepository.findById(productCategoryId)).thenReturn(Optional.of(productCategory));

        ProductCategoryResponse response = productCategoryService.getProductCategoryToDto(productCategory.getId());

        assertEquals(expectedResponse, response);

    }

    @Test
    void getProductCategoryToSimpleDto() {
        Long productCategoryId = 1L;
        ProductCategory productCategory = new ProductCategory().setName("AFK").setImage("ALD");
        productCategory.setId(productCategoryId);
        ProductCategoryResponse expectedResponse = ProductCategoryResponse.builder().id(1L).name("AFK").image("ALD").build();
        when(productCategoryRepository.findById(productCategoryId)).thenReturn(Optional.of(productCategory));

        ProductCategoryResponse response = productCategoryService.getProductCategoryToSimpleDto(productCategory.getId());

        assertEquals(expectedResponse, response);
    }

    @Test
    void getAllProductCategories() {

        List<ProductCategory> productCategories = new ArrayList<>();
        productCategories.add(new ProductCategory());
        productCategories.add(new ProductCategory());

        when(productCategoryRepository.findAll(Sort.by(Sort.Direction.ASC, "name"))).thenReturn(productCategories);

        List<ProductCategoryResponse> response = productCategoryService.getAllProductCategories();

        assertNotNull(response);
        assertEquals(productCategories.size(), response.size());
        verify(productCategoryRepository, times(1)).findAll((Sort.by(Sort.Direction.ASC, "name")));
    }

    @Test
    void getAllProductCategoriesByOrderByCreateAt() {
        int pageIndex = 1;
        String query = "";
        ProductCategoryRequest request = new ProductCategoryRequest();
        request.setPageIndex(pageIndex);
        request.setQuery(query);

        List<ProductCategory> productCategories = new ArrayList<>();
        productCategories.add(new ProductCategory());
        productCategories.add(new ProductCategory());
        productCategories.add(new ProductCategory());
        Page<ProductCategory> productCategoryPage = new PageImpl<>(productCategories);

        when(productCategoryRepository.findAll((Specification<ProductCategory>) any(), any(PageRequest.class))).thenReturn(productCategoryPage);

        Page<ProductCategoryResponse> responseByPage = productCategoryService.getAllProductCategoriesByOrderByCreateAt(request);

        assertNotNull(responseByPage);
        assertEquals(productCategories.size(), responseByPage.getTotalElements());
        verify(productCategoryRepository, times(1)).findAll((Specification<ProductCategory>) any(), any(PageRequest.class));
    }

    @Test
    void saveProductCategory() throws Exception {
        ClassPathResource resource = new ClassPathResource("img/img.png");
        MockMultipartFile image = new MockMultipartFile("image", "image", "imag/png", resource.getInputStream());
        ProductCategorySaveRequest request = new ProductCategorySaveRequest();
        request.setName("Name");
        request.setImage(image);

        productCategoryService.saveProductCategory(request);

        verify(productCategoryRepository, times(1)).save(any(ProductCategory.class));
    }

    @Test
    void updateProductCategory() throws Exception {
        ClassPathResource resource = new ClassPathResource("img/img.png");
        MockMultipartFile image = new MockMultipartFile("image", "image", "imag/png", resource.getInputStream());
        ProductCategoryUpdateRequest request = new ProductCategoryUpdateRequest();
        request.setId(1L);
        request.setName("Name");
        request.setImage(image);
        ProductCategory productCategory = new ProductCategory().setName("name").setImage("ima");
        productCategory.setId(1L);

        when(productCategoryRepository.findById(request.getId())).thenReturn(Optional.of(productCategory));

        productCategoryService.updateProductCategory(request);

        verify(productCategoryRepository, times(1)).save(any(ProductCategory.class));
    }

    @Test
    void deleteProductCategoryById() {
        Long bannerId = 1L;
        ProductCategory productCategory = new ProductCategory();
        productCategory.setId(bannerId);

        when(productCategoryRepository.findById(bannerId)).thenReturn(Optional.of(productCategory));

        productCategoryService.deleteProductCategoryById(bannerId);

        verify(productCategoryRepository, times(1)).findById(bannerId);
        verify(productCategoryRepository, times(1)).delete(productCategory);
    }
}