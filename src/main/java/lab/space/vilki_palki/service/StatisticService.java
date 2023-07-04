package lab.space.vilki_palki.service;

import lab.space.vilki_palki.model.product.ProductResponse;
import lab.space.vilki_palki.model.promotion.PromotionResponse;
import lab.space.vilki_palki.model.structure.StructureResponse;

import java.util.List;

public interface StatisticService {
    List<ProductResponse> getAllProducts();

    List<StructureResponse> getAllStructures();

    List<PromotionResponse> getAllPromotions();

    Integer getCountOfUsers();

    Integer getCountOfProductCategories();

    Integer getCountOfStructureCategories();

    List<Integer> getAllBirthMonth();

    Integer getCountByOrdersWithDoneStatus();
    Integer getCountByOrdersWithCanceledStatus();
    List<Integer> getCountByOrdersWithDoneStatusByMonth();
    List<Long> getTotalPriceByOrdersWithDoneStatusByMonth();
}
