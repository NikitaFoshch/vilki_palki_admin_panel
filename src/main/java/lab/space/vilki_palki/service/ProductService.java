package lab.space.vilki_palki.service;

import lab.space.vilki_palki.entity.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {
    List<Product> getAllProductsByOrderByCreateAt();
    Product getProductById(Long id);
    void saveProduct(Product product, MultipartFile image);
    void updateProductById(Long id, Product product, MultipartFile image);
    void deleteProduct(Long id);
}
