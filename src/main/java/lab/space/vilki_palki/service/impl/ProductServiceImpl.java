package lab.space.vilki_palki.service.impl;

import lab.space.vilki_palki.entity.Product;
import lab.space.vilki_palki.entity.Structure;
import lab.space.vilki_palki.mapper.ProductMapper;
import lab.space.vilki_palki.model.product.ProductResponse;
import lab.space.vilki_palki.model.product.ProductSaveRequest;
import lab.space.vilki_palki.model.product.ProductUpdateRequest;
import lab.space.vilki_palki.repository.ProductRepository;
import lab.space.vilki_palki.service.ProductCategoryService;
import lab.space.vilki_palki.service.ProductService;
import lab.space.vilki_palki.service.ProductTypeService;
import lab.space.vilki_palki.service.StructureService;
import lab.space.vilki_palki.util.FileUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

@Service
@Slf4j
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final StructureService structureService;
    private final ProductTypeService productTypeService;
    private final ProductCategoryService productCategoryService;


    @Override
    public List<ProductResponse> getAllProductsSimpleDtoWithImageByOrderByCreateAt() {
        return productRepository.findAll(Sort.by(Sort.Direction.DESC, "createAt"))
                .stream()
                .map(productMapper::toSimpleDtoWithImage)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductResponse> getAllProductsSimpleDtoByOrderByName() {
        return productRepository.findAll(Sort.by(Sort.Direction.DESC, "name"))
                .stream()
                .map(productMapper::toSimpleDto)
                .collect(Collectors.toList());
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
    public ProductResponse getProductSimpleDto(Long id) {
        return productMapper.toSimpleDto(getProduct(id));
    }

    @Override
    public void saveProduct(ProductSaveRequest request) {

        Product product = new Product()
                .setProductInfo(request.getProductInfo())
                .setProductCategory(productCategoryService.getProductCategoryById(request.getProductsCategoryId()))
                .setProductType(productTypeService.getProductTypeById(request.getProductsTypeId()))
                .setPrice(request.getPrice())
                .setDescription(request.getDescription())
                .setStructures(
                        request.getStructureIds()
                                .stream()
                                .map(structureService::getById)
                                .collect(Collectors.toList())
                )
                .setName(request.getName());
        final String newFileName = UUID.randomUUID() + request.getImage().getOriginalFilename();
        FileUtil.saveFile(newFileName, request.getImage());
        product.setImage(newFileName);
        productRepository.save(product);
    }

    @Override
    public void updateProductById(ProductUpdateRequest request) {
        Product product = getProduct(request.getId())
                .setProductInfo(request.getProductInfo())
                .setProductCategory(productCategoryService.getProductCategoryById(request.getProductsCategoryId()))
                .setProductType(productTypeService.getProductTypeById(request.getProductsTypeId()))
                .setPrice(request.getPrice())
                .setDescription(request.getDescription())
                .setName(request.getName());
        List<Structure> structures = new ArrayList<>(product.getStructures());
        structures.clear();
        structures.addAll(request.getStructureIds().stream().map(structureService::getById).collect(Collectors.toList()));
        product.setStructures(structures);
        final String newFileName = UUID.randomUUID() + request.getImage().getOriginalFilename();
        FileUtil.saveFile(newFileName, request.getImage());
        FileUtil.deleteFile(product.getImage());
        product.setImage(newFileName);
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
