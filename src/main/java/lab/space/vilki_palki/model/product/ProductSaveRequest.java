package lab.space.vilki_palki.model.product;

import lab.space.vilki_palki.entity.ProductsCategory;
import lab.space.vilki_palki.entity.Structure;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public record ProductSaveRequest (
        String name,
        String productInfo,
        Integer price,
        String description,
        MultipartFile image,
        Long productsCategoryId,
        Long productsTypeId,
        List<Long> structuresId
){
}
