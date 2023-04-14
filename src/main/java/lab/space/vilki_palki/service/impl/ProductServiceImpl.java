package lab.space.vilki_palki.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lab.space.vilki_palki.entity.Product;
import lab.space.vilki_palki.repository.ProductRepository;
import lab.space.vilki_palki.service.ProductService;
import lab.space.vilki_palki.util.FileUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public List<Product> getAllProductsByOrderByCreateAt() {
        log.info("---------------Get All Product Sort By createAt DESC---------------");
        return productRepository.findAll(Sort.by(Sort.Direction.DESC, "createAt"));
    }

    @Override
    public Product getProductById(Long id) {
        log.info("---------------Get Product By Id" + id + "---------------");
        return productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product no found with id " + id));
    }

    @Override
    public void saveProduct(Product requestProduct, MultipartFile image) {
        log.info("---------------Save Product---------------");
        if (FileUtil.saveFile(image.getOriginalFilename(), image))
            requestProduct.setImage(image.getOriginalFilename());
        productRepository.save(requestProduct);
        log.info("---------------Success Save Product---------------");
    }

    @Override
    public void updateProductById(Long id, Product requestProduct, MultipartFile image) {
        log.info("---------------Update Product---------------");
        Product product = getProductById(id);
        product.setProductInfo(requestProduct.getProductInfo());
        product.setName(requestProduct.getName());
        product.setDescription(requestProduct.getDescription());

        if (FileUtil.saveFile(image.getOriginalFilename(), image)) {
            FileUtil.deleteFile(product.getImage());
            requestProduct.setImage(image.getOriginalFilename());
        }

        productRepository.save(product);
        log.info("---------------Success Update Product---------------");

    }

    @Override
    public void deleteProduct(Long id) {
        log.info("---------------Delete Product---------------");
        Product product = getProductById(id);
        if (product.getImage() != null) {
            FileUtil.deleteFile(product.getImage());
        }
        productRepository.delete(product);
        log.info("---------------Delete Update Product---------------");
    }
}
