package lab.space.vilki_palki.service.impl;

import lab.space.vilki_palki.entity.ProductCategory;
import lab.space.vilki_palki.mapper.ProductCategoryMapper;
import lab.space.vilki_palki.model.product_category.ProductCategoryRequest;
import lab.space.vilki_palki.model.product_category.ProductCategoryResponse;
import lab.space.vilki_palki.model.product_category.ProductCategorySaveRequest;
import lab.space.vilki_palki.model.product_category.ProductCategoryUpdateRequest;
import lab.space.vilki_palki.repository.ProductCategoryRepository;
import lab.space.vilki_palki.service.ProductCategoryService;
import lab.space.vilki_palki.util.FileUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

@Service
@AllArgsConstructor
@Slf4j
public class ProductCategoryServiceImpl implements ProductCategoryService {
    private final ProductCategoryRepository repository;
    private final ProductCategorySpecification productCategorySpecification;

    @Override
    public ProductCategory getProductCategoryById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ProductCategory not found by id " + id));
    }

    @Override
    public ProductCategoryResponse getProductCategoryToDto(Long id) {
        return ProductCategoryMapper.toDto(getProductCategoryById(id));
    }

    @Override
    public ProductCategoryResponse getProductCategoryToSimpleDto(Long id) {
        return ProductCategoryMapper.toDto(getProductCategoryById(id));
    }

    @Override
    public List<ProductCategoryResponse> getAllProductCategories() {
        return repository.findAll(Sort.by(Sort.Direction.ASC, "name"))
                .stream()
                .map(ProductCategoryMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<ProductCategoryResponse> getAllProductCategoriesByOrderByCreateAt(ProductCategoryRequest request) {
        final int DEFAULT_PAGE_SIZE = 5;
        return repository.findAll(productCategorySpecification.getProductCategoriesByRequest(request),
                PageRequest.of(request.getPageIndex(), DEFAULT_PAGE_SIZE)).map(ProductCategoryMapper::toDto);
    }

    @Override
    public void saveProductCategory(ProductCategorySaveRequest request) {
        ProductCategory productCategory = new ProductCategory()
                .setName(request.getName());
        final String newFileName = UUID.randomUUID() + request.getImage().getOriginalFilename();
        FileUtil.saveFile(newFileName, request.getImage());
        FileUtil.deleteFile(productCategory.getImage());
        productCategory.setImage(newFileName);
        repository.save(productCategory);

    }

    @Override
    public void updateProductCategory(ProductCategoryUpdateRequest request) {
        ProductCategory productCategory = getProductCategoryById(request.getId())
                .setName(request.getName());
        if (nonNull(request.getImage())
                && nonNull(request.getImage().getOriginalFilename())
                && !request.getImage().getOriginalFilename().equals("")) {
            final String newFileName = UUID.randomUUID() + request.getImage().getOriginalFilename();
            FileUtil.saveFile(newFileName, request.getImage());
            FileUtil.deleteFile(productCategory.getImage());
            productCategory.setImage(newFileName);
        }
        repository.save(productCategory);

    }

    @Override
    public void deleteProductCategoryById(Long id) {
        ProductCategory productCategory = getProductCategoryById(id);
        if (nonNull(productCategory.getImage())) FileUtil.deleteFile(productCategory.getImage());
        repository.delete(productCategory);
    }
}
