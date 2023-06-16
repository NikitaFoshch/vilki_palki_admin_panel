package lab.space.vilki_palki.service.impl;

import lab.space.vilki_palki.entity.Product;
import lab.space.vilki_palki.entity.Promotion;
import lab.space.vilki_palki.mapper.PromotionMapper;
import lab.space.vilki_palki.model.promotion.PromotionResponse;
import lab.space.vilki_palki.model.promotion.PromotionSaveRequest;
import lab.space.vilki_palki.model.promotion.PromotionUpdateRequest;
import lab.space.vilki_palki.repository.PromotionRepository;
import lab.space.vilki_palki.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Sort;
import org.springframework.mock.web.MockMultipartFile;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PromotionsServiceImplTest {

    @Mock
    private PromotionRepository promotionRepository;
    @Mock
    private PromotionMapper promotionMapper;
    @Mock
    private ProductService productService;

    @InjectMocks
    private PromotionsServiceImpl promotionsService;

    @Test
    void getAllPromotionsByOrderByCreateAt() {
        List<Promotion> promotions = List.of(new Promotion(), new Promotion());
        promotions.get(0).setId(1L);
        promotions.get(0).setName("123");
        promotions.get(0).setImage("132");
        promotions.get(1).setId(2L);
        promotions.get(1).setName("5435");
        promotions.get(1).setImage("5734");
        List<PromotionResponse> responses = List.of(
                new PromotionResponse(1L, "123", null, null, "132", null),
                new PromotionResponse(2L, "5435", null, null, "5734", null)
        );

        when(promotionRepository.findAll(Sort.by(Sort.Direction.DESC, "createAt"))).thenReturn(promotions);
        when(promotionMapper.toSimpleDto(promotions.get(0))).thenReturn(responses.get(0));
        when(promotionMapper.toSimpleDto(promotions.get(1))).thenReturn(responses.get(1));
        List<PromotionResponse> actualResponses = promotionsService.getAllPromotionsByOrderByCreateAt();

        assertEquals(responses, actualResponses);
    }

    @Test
    void getPromotionById() {
        Long promotionId = 1L;
        Promotion promotion = new Promotion();
        promotion.setId(promotionId);

        when(promotionRepository.findById(promotionId)).thenReturn(Optional.of(promotion));

        Promotion promotion1 = promotionsService.getPromotionById(promotionId);
        assertEquals(promotion, promotion1);
        verify(promotionRepository, times(1)).findById(promotionId);
    }

    @Test
    void getPromotionDto() {
        Long id = 1L;
        Promotion promotion = new Promotion().setName("Bober").setImage("bober");
        promotion.setId(1L);
        PromotionResponse expectedResponse = new PromotionResponse(1L, "Bober", null, null, "bober", null);
        when(promotionRepository.findById(id)).thenReturn(Optional.of(promotion));
        when(promotionMapper.toDto(promotion)).thenReturn(expectedResponse);

        PromotionResponse response = promotionsService.getPromotionDto(promotion.getId());

        assertEquals(expectedResponse, response);
    }

    @Test
    void savePromotion() throws Exception {
        ClassPathResource resource = new ClassPathResource("img/img.png");
        MockMultipartFile image = new MockMultipartFile("image", "image", "imag/png", resource.getInputStream());
        PromotionSaveRequest request = new PromotionSaveRequest(50L, "Name", null, image, 1L);
        Product product = new Product();
        product.setId(1L);
        product.setPrice(BigDecimal.valueOf(100));

        when(productService.getProduct(request.productId())).thenReturn(product);

        promotionsService.savePromotion(request);

        verify(promotionRepository, times(1)).save(any(Promotion.class));
    }

    @Test
    void updatePromotionById() throws Exception {
        ClassPathResource resource = new ClassPathResource("img/img.png");
        MockMultipartFile image = new MockMultipartFile("image", "image", "imag/png", resource.getInputStream());
        PromotionUpdateRequest request = new PromotionUpdateRequest(1L, 100L, "Name", null, image, 1L);
        Product product = new Product();
        product.setId(1L);
        product.setPrice(BigDecimal.valueOf(100));
        Promotion promotion = new Promotion().setName("name").setImage("ima");
        promotion.setId(1L);

        when(productService.getProduct(request.productId())).thenReturn(product);
        when(promotionRepository.findById(request.id())).thenReturn(Optional.of(promotion));
        promotionsService.updatePromotionById(request);

        verify(promotionRepository, times(1)).save(any(Promotion.class));
    }

    @Test
    void deletePromotion() {
        Long bannerId = 1L;
        Promotion promotion = new Promotion();
        promotion.setId(bannerId);

        when(promotionRepository.findById(bannerId)).thenReturn(Optional.of(promotion));

        promotionsService.deletePromotion(bannerId);

        verify(promotionRepository, times(1)).findById(bannerId);
        verify(promotionRepository, times(1)).delete(promotion);
    }
}