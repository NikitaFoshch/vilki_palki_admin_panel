package lab.space.vilki_palki.service.impl;

import lab.space.vilki_palki.entity.Product;
import lab.space.vilki_palki.entity.Structure;
import lab.space.vilki_palki.mapper.ProductMapper;
import lab.space.vilki_palki.model.product.ProductResponse;
import lab.space.vilki_palki.model.product.ProductSaveRequest;
import lab.space.vilki_palki.model.product.ProductUpdateRequest;
import lab.space.vilki_palki.repository.ProductRepository;
import lab.space.vilki_palki.repository.StructureRepository;
import lab.space.vilki_palki.service.ProductCategoryService;
import lab.space.vilki_palki.service.ProductTypeService;
import lab.space.vilki_palki.service.StructureService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mock.web.MockMultipartFile;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;
    @Mock
    private StructureRepository structureRepository;
    @Mock
    private ProductMapper productMapper;
    @Mock
    private StructureService structureService;
    @Mock
    private ProductTypeService productTypeService;
    @Mock
    private ProductCategoryService productCategoryService;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    void getAllProductsSimpleDtoWithImageByOrderByCreateAt() {
    }

    @Test
    void getAllProductsSimpleDtoByOrderByName() {
    }

    @Test
    void getProduct() {
        Long productId = 1L;
        Product product = new Product();
        product.setId(productId);

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        Product product1 = productService.getProduct(productId);
        assertEquals(product, product1);
        verify(productRepository, times(1)).findById(productId);
    }

    @Test
    void getProductDto() {
        Long id = 1L;
        Product product = new Product().setName("Bober");
        product.setId(1L);
        ProductResponse expectedResponse = new ProductResponse(
                1L, "Bober", BigDecimal.valueOf(1),
                "fqaf", "fqef", "qwdrwq",
                null, null, null
        );
        when(productRepository.findById(id)).thenReturn(Optional.of(product));
        when(productMapper.toDto(product)).thenReturn(expectedResponse);

        ProductResponse response = productService.getProductDto(product.getId());

        assertEquals(expectedResponse, response);
    }

    @Test
    void getProductSimpleDto() {
        Long id = 1L;
        Product product = new Product().setName("Bober");
        product.setId(1L);
        ProductResponse expectedResponse = new ProductResponse(
                1L, "Bober", BigDecimal.valueOf(1),
                "fqaf", "fqef", "qwdrwq",
                null, null, null
        );
        when(productRepository.findById(id)).thenReturn(Optional.of(product));
        when(productMapper.toSimpleDto(product)).thenReturn(expectedResponse);

        ProductResponse response = productService.getProductSimpleDto(product.getId());

        assertEquals(expectedResponse, response);
    }

    @Test
    void saveProduct() throws Exception {
        ClassPathResource resource = new ClassPathResource("img/img.png");
        MockMultipartFile image = new MockMultipartFile("image", "image", "imag/png", resource.getInputStream());
        ProductSaveRequest request = new ProductSaveRequest(
                "Name",
                "hgfdh",
                BigDecimal.valueOf(1),
                "adsf",
                image,
                1L,
                1L,
                List.of(1L, 2L)
        );

        productService.saveProduct(request);

        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    void updateProductById() throws Exception {
        ClassPathResource resource = new ClassPathResource("img/img.png");
        MockMultipartFile image = new MockMultipartFile("image", "image", "imag/png", resource.getInputStream());
        List<Long> number = List.of(1L, 2L);
        ProductUpdateRequest request = new ProductUpdateRequest(
                1L,
                "Name",
                "hgfdh",
                BigDecimal.valueOf(1),
                "adsf",
                image,
                1L,
                1L,
                number
        );
        Product product = new Product();
        product.setId(1L);
        product.setStructures(List.of(new Structure(), new Structure()));
        Structure structure = new Structure();
        structure.setId(1L);
        when(productRepository.findById(request.id())).thenReturn(Optional.of(product));
        when(structureRepository.findById(1L)).thenReturn(Optional.of(structure));
        productService.updateProductById(request);

        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    void deleteProduct() {
        Long bannerId = 1L;
        Product product = new Product();
        product.setId(bannerId);

        when(productRepository.findById(bannerId)).thenReturn(Optional.of(product));

        productService.deleteProduct(bannerId);

        verify(productRepository, times(1)).findById(bannerId);
        verify(productRepository, times(1)).delete(product);
    }
}