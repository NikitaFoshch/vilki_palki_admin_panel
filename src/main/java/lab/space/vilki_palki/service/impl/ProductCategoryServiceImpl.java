package lab.space.vilki_palki.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lab.space.vilki_palki.entity.ProductCategory;
import lab.space.vilki_palki.mapper.ProductCategoryMapper;
import lab.space.vilki_palki.model.product_category.ProductCategoryResponse;
import lab.space.vilki_palki.repository.ProductCategoryRepository;
import lab.space.vilki_palki.service.ProductCategoryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ProductCategoryServiceImpl  implements ProductCategoryService {
    private final ProductCategoryRepository repository;
    @Override
    public ProductCategory getProductCategoryById(Long id) {
        return repository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("ProductCategory not found by id " + id));
    }

    @Override
    public ProductCategoryResponse getProductCategoryToDto(Long id) {
        return ProductCategoryMapper.toDto(getProductCategoryById(id));
    }

    @Override
    public List<ProductCategoryResponse> getAllProductCategories() {
        return repository.findAll(Sort.by(Sort.Direction.ASC,"name"))
                .stream()
                .map(ProductCategoryMapper::toDto)
                .toList();
    }
}
