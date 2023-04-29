package lab.space.vilki_palki.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lab.space.vilki_palki.entity.Product;
import lab.space.vilki_palki.mapper.ProductMapper;
import lab.space.vilki_palki.model.product.ProductResponse;
import lab.space.vilki_palki.model.product.ProductSaveRequest;
import lab.space.vilki_palki.model.product.ProductUpdateRequest;
import lab.space.vilki_palki.repository.ProductRepository;
import lab.space.vilki_palki.service.ProductService;
import lab.space.vilki_palki.util.FileUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static java.util.Objects.nonNull;

@Service
@Slf4j
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;


    @Override
    public List<ProductResponse> getAllProductsByOrderByCreateAt() {
        return productRepository.findAll(Sort.by(Sort.Direction.DESC, "createAt"))
                .stream()
                .map(productMapper::toDto)
                .toList();
    }

    @Override
    public Product getProduct(Long id) {
        log.info("---------------Get Product By Id" + id + "---------------");
        return productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product no found with id " + id));
    }

    @Override
    public ProductResponse getProductDto(Long id) {
        return productMapper.toDto(getProduct(id));
    }

    @Override
    public void saveProduct(ProductSaveRequest request) {

        Product product = new Product()
                .setProductInfo(request.productInfo())
                .setProductsCategory(null)
                .setProductsType(null)
                .setPrice(request.price())
                .setDescription(request.description())
                .setStructures(null)
                .setName(request.name());
        if (nonNull(request.image())
                && nonNull(request.image().getOriginalFilename())
                && !request.image().getOriginalFilename().equals("")) {
            final String newFileName = UUID.randomUUID() + request.image().getOriginalFilename();
            FileUtil.saveFile(newFileName, request.image());
            product.setImage(newFileName);
        }
        productRepository.save(product);
    }

    @Override
    public void updateProductById(ProductUpdateRequest request) {
        Product product = getProduct(request.id())
                .setProductInfo(request.productInfo())
                .setProductsCategory(null)
                .setProductsType(null)
                .setPrice(request.price())
                .setDescription(request.description())
                .setStructures(null)
                .setName(request.name());
        if (nonNull(request.image())
                && nonNull(request.image().getOriginalFilename())
                && !request.image().getOriginalFilename().equals("")) {
            final String newFileName = UUID.randomUUID() + request.image().getOriginalFilename();
            FileUtil.saveFile(newFileName, request.image());
            FileUtil.deleteFile(product.getImage());
            product.setImage(newFileName);
        }
        productRepository.save(product);
    }


    @Override
    public void deleteProduct(Long id) {
        log.info("---------------Delete Product---------------");
        Product product = getProduct(id);
        if (nonNull(product.getImage())) FileUtil.deleteFile(product.getImage());
        productRepository.delete(product);
        log.info("---------------Delete Update Product---------------");
    }
}
