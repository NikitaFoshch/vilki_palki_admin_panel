package lab.space.vilki_palki.model.product;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public record ProductSaveRequest(
        String name,
        String productInfo,
        Integer price,
        String description,
        MultipartFile image,
        Long productsCategoryId,
        Long productsTypeId,
        List<Long> structuresId
) {
}
