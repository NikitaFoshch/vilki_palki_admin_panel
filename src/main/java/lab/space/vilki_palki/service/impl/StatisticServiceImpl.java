package lab.space.vilki_palki.service.impl;

import lab.space.vilki_palki.entity.User;
import lab.space.vilki_palki.model.product.ProductResponse;
import lab.space.vilki_palki.model.promotion.PromotionResponse;
import lab.space.vilki_palki.model.structure.StructureResponse;
import lab.space.vilki_palki.service.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class StatisticServiceImpl implements StatisticService {
    private final ProductService productService;
    private final StructureService structureService;
    private final PromotionService promotionService;
    private final UserService userService;
    private final ProductCategoryService productCategoryService;
    private final StructureCategoryService structureCategoryService;

    @Override
    public List<ProductResponse> getAllProducts() {
        return productService.getAllProductsSimpleDtoByOrderByName();
    }

    @Override
    public List<StructureResponse> getAllStructures() {
        return structureService.getAllProductStructuresByOrderByName();
    }

    @Override
    public List<PromotionResponse> getAllPromotions() {
        return promotionService.getAllPromotionsByOrderByCreateAt();
    }

    @Override
    public Integer getCountOfUsers() {
        return userService.getCountByAllUsers();
    }

    @Override
    public Integer getCountOfProductCategories() {
        return productCategoryService.getAllProductCategories().size();
    }

    @Override
    public Integer getCountOfStructureCategories() {
        return structureCategoryService.getAllStructureCategories().size();
    }

    @Override
    public List<Integer> getAllBirthMonth() {
        List<User> users = userService.getAllUsers();
        List<Integer> month = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            int count = 0;

            for (User user : users) {
                if (i == user.getBirthday().atZone(ZoneId.systemDefault()).getMonthValue()) count++;
            }
            month.add(count);
        }
        return month;
    }
}
