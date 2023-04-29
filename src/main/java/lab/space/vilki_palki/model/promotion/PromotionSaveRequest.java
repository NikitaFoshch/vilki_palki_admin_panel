package lab.space.vilki_palki.model.promotion;

import lab.space.vilki_palki.entity.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public record PromotionSaveRequest (
        Integer percent,
        String name,
        Integer totalPrice,
        MultipartFile image,
        List<Product> products
){
}
