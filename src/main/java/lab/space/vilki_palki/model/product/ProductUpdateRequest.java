package lab.space.vilki_palki.model.product;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public record ProductUpdateRequest(
        Long id,
        String name,
        Integer price,
        String productInfo,
        String description,
        MultipartFile image,
        Long productsCategoryId,
        Long productsTypeId,

        List<Long> structuresId
) {
}
