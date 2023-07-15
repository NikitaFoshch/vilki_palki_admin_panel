package lab.space.vilki_palki.service.impl;

import lab.space.vilki_palki.entity.User;
import lab.space.vilki_palki.model.product.ProductResponse;
import lab.space.vilki_palki.model.product_category.ProductCategoryResponse;
import lab.space.vilki_palki.model.promotion.PromotionResponse;
import lab.space.vilki_palki.model.structure.StructureResponse;
import lab.space.vilki_palki.model.structure_category.StructureCategoryResponse;
import lab.space.vilki_palki.service.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StatisticServiceImplTest {
    @Mock
    private UserService userService;
    @Mock
    private ProductService productService;

    @Mock
    private StructureService structureService;

    @Mock
    private PromotionService promotionService;

    @Mock
    private ProductCategoryService productCategoryService;

    @Mock
    private StructureCategoryService structureCategoryService;

    @Mock
    private OrderService orderService;

    @InjectMocks
    private StatisticServiceImpl statisticService;

    @Test
    void getAllBirthMonth() {
        List<User> users = List.of(new User(), new User(), new User());
        users.get(0).setId(1L);
        users.get(0).setName("123");
        users.get(0).setBirthday(Instant.now());
        users.get(1).setId(2L);
        users.get(1).setName("5435");
        users.get(1).setBirthday(Instant.now());
        users.get(2).setId(3L);
        users.get(2).setName("33");
        users.get(2).setBirthday(Instant.now());

        when(userService.getAllUsers()).thenReturn(users);
        List<Integer> actualResponses = statisticService.getAllBirthMonth();
        System.out.println(actualResponses);
        assertNotNull(actualResponses);
    }

    @Test
    void testGetAllProducts_ReturnsAllProducts() {
        List<ProductResponse> expectedProducts = Arrays.asList(
                ProductResponse.builder().build(), ProductResponse.builder().build(), ProductResponse.builder().build()
        );

        when(productService.getAllProductsSimpleDtoByOrderByName()).thenReturn(expectedProducts);

        StatisticService statisticService = new StatisticServiceImpl(
                productService, structureService, promotionService, userService,
                productCategoryService, structureCategoryService, orderService
        );

        List<ProductResponse> result = statisticService.getAllProducts();

        assertEquals(expectedProducts, result);

        verify(productService, times(1)).getAllProductsSimpleDtoByOrderByName();
    }

    @Test
    void testGetAllStructures_ReturnsAllStructures() {
        List<StructureResponse> expectedStructures = Arrays.asList(
                StructureResponse.builder().build(), StructureResponse.builder().build(), StructureResponse.builder().build()
        );

        when(structureService.getAllProductStructuresByOrderByName()).thenReturn(expectedStructures);

        StatisticService statisticService = new StatisticServiceImpl(
                productService, structureService, promotionService, userService,
                productCategoryService, structureCategoryService, orderService
        );

        List<StructureResponse> result = statisticService.getAllStructures();

        assertEquals(expectedStructures, result);

        verify(structureService, times(1)).getAllProductStructuresByOrderByName();
    }

    @Test
    void testGetAllPromotions_ReturnsAllPromotions() {
        List<PromotionResponse> expectedPromotions = Arrays.asList(
                PromotionResponse.builder().build(), PromotionResponse.builder().build(), PromotionResponse.builder().build()
        );

        when(promotionService.getAllPromotionsByOrderByCreateAt()).thenReturn(expectedPromotions);

        StatisticService statisticService = new StatisticServiceImpl(
                productService, structureService, promotionService, userService,
                productCategoryService, structureCategoryService, orderService
        );

        List<PromotionResponse> result = statisticService.getAllPromotions();

        assertEquals(expectedPromotions, result);

        verify(promotionService, times(1)).getAllPromotionsByOrderByCreateAt();
    }

    @Test
    void testGetCountOfUsers_ReturnsCountOfUsers() {
        int expectedCount = 10;

        when(userService.getCountByAllUsers()).thenReturn(expectedCount);

        StatisticService statisticService = new StatisticServiceImpl(
                productService, structureService, promotionService, userService,
                productCategoryService, structureCategoryService, orderService
        );

        int result = statisticService.getCountOfUsers();

        assertEquals(expectedCount, result);

        verify(userService, times(1)).getCountByAllUsers();
    }

    @Test
    void testGetCountOfProductCategories_ReturnsCountOfProductCategories() {
        int expectedCount = 3;

        List<ProductCategoryResponse> productCategories = Arrays.asList(
                ProductCategoryResponse.builder().build(), ProductCategoryResponse.builder().build(), ProductCategoryResponse.builder().build()
        );

        when(productCategoryService.getAllProductCategories()).thenReturn(productCategories);

        StatisticService statisticService = new StatisticServiceImpl(
                productService, structureService, promotionService, userService,
                productCategoryService, structureCategoryService, orderService
        );

        int result = statisticService.getCountOfProductCategories();

        assertEquals(expectedCount, result);

        verify(productCategoryService, times(1)).getAllProductCategories();
    }

    @Test
    void testGetCountOfStructureCategories_ReturnsCountOfStructureCategories() {
        int expectedCount = 2;

        List<StructureCategoryResponse> structureCategories = Arrays.asList(
                StructureCategoryResponse.builder().build(), StructureCategoryResponse.builder().build()
        );

        when(structureCategoryService.getAllStructureCategories()).thenReturn(structureCategories);

        StatisticService statisticService = new StatisticServiceImpl(
                productService, structureService, promotionService, userService,
                productCategoryService, structureCategoryService, orderService
        );

        int result = statisticService.getCountOfStructureCategories();

        assertEquals(expectedCount, result);

        verify(structureCategoryService, times(1)).getAllStructureCategories();
    }

    @Test
    void testGetCountByOrdersWithDoneStatus_ReturnsCountOfOrdersWithDoneStatus() {
        int expectedCount = 15;

        when(orderService.getCountByOrdersWithDoneStatus()).thenReturn(expectedCount);

        StatisticService statisticService = new StatisticServiceImpl(
                productService, structureService, promotionService, userService,
                productCategoryService, structureCategoryService, orderService
        );

        int result = statisticService.getCountByOrdersWithDoneStatus();

        assertEquals(expectedCount, result);

        verify(orderService, times(1)).getCountByOrdersWithDoneStatus();
    }

    @Test
    void testGetCountByOrdersWithCanceledStatus_ReturnsCountOfOrdersWithCanceledStatus() {
        int expectedCount = 5;

        when(orderService.getCountByOrdersWithCanceledStatus()).thenReturn(expectedCount);

        StatisticService statisticService = new StatisticServiceImpl(
                productService, structureService, promotionService, userService,
                productCategoryService, structureCategoryService, orderService
        );

        int result = statisticService.getCountByOrdersWithCanceledStatus();

        assertEquals(expectedCount, result);

        verify(orderService, times(1)).getCountByOrdersWithCanceledStatus();
    }

    @Test
    void testGetCountByOrdersWithDoneStatusByMonth_ReturnsCountOfOrdersWithDoneStatusByMonth() {
        List<Integer> expectedCountsByMonth = Arrays.asList(5, 3, 6, 4, 2, 1, 0, 0, 0, 0, 0, 0);

        when(orderService.getCountByOrdersWithDoneStatusByMonth()).thenReturn(expectedCountsByMonth);

        StatisticService statisticService = new StatisticServiceImpl(
                productService, structureService, promotionService, userService,
                productCategoryService, structureCategoryService, orderService
        );

        List<Integer> result = statisticService.getCountByOrdersWithDoneStatusByMonth();

        assertEquals(expectedCountsByMonth, result);

        verify(orderService, times(1)).getCountByOrdersWithDoneStatusByMonth();
    }

    @Test
    void testGetTotalPriceByOrdersWithDoneStatusByMonth_ReturnsTotalPriceByOrdersWithDoneStatusByMonth() {
        List<Long> expectedTotalPricesByMonth = Arrays.asList(5000L, 3000L, 6000L, 4000L, 2000L, 1000L, 0L, 0L, 0L, 0L, 0L, 0L);

        when(orderService.getTotalPriceByOrdersWithDoneStatusByMonth()).thenReturn(expectedTotalPricesByMonth);

        StatisticService statisticService = new StatisticServiceImpl(
                productService, structureService, promotionService, userService,
                productCategoryService, structureCategoryService, orderService
        );

        List<Long> result = statisticService.getTotalPriceByOrdersWithDoneStatusByMonth();

        assertEquals(expectedTotalPricesByMonth, result);

        verify(orderService, times(1)).getTotalPriceByOrdersWithDoneStatusByMonth();
    }
}