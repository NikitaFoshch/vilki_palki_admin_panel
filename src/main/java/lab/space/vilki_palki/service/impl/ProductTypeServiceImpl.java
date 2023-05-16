package lab.space.vilki_palki.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lab.space.vilki_palki.entity.ProductType;
import lab.space.vilki_palki.mapper.ProductTypeMapper;
import lab.space.vilki_palki.model.product_type.ProductTypeRequest;
import lab.space.vilki_palki.model.product_type.ProductTypeResponse;
import lab.space.vilki_palki.model.product_type.ProductTypeSaveRequest;
import lab.space.vilki_palki.model.product_type.ProductTypeUpdateRequest;
import lab.space.vilki_palki.repository.ProductTypeRepository;
import lab.space.vilki_palki.service.ProductTypeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ProductTypeServiceImpl implements ProductTypeService {
    private final ProductTypeRepository repository;
    private final ProductTypeSpecification productTypeSpecification;

    @Override
    public ProductType getProductTypeById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ProductType not found by id " + id));
    }

    @Override
    public ProductTypeResponse getProductTypeToDto(Long id) {
        return ProductTypeMapper.toDto(getProductTypeById(id));
    }

    @Override
    public List<ProductTypeResponse> getAllProductTypes() {
        return repository.findAll(Sort.by(Sort.Direction.ASC, "name"))
                .stream()
                .map(ProductTypeMapper::toDto)
                .toList();
    }

    @Override
    public Page<ProductTypeResponse> getAllProductTypesByOrderByCreateAt(ProductTypeRequest request) {
        final int DEFAULT_PAGE_SIZE = 5;
        return repository.findAll(productTypeSpecification.getProductTypesByRequest(request),
                PageRequest.of(request.getPageIndex(), DEFAULT_PAGE_SIZE)).map(ProductTypeMapper::toDto);
    }

    @Override
    public void saveProductType(ProductTypeSaveRequest request) {
        ProductType productType = new ProductType()
                .setName(request.name());
        repository.save(productType);
    }

    @Override
    public void updateProductType(ProductTypeUpdateRequest request) {
        ProductType productType = getProductTypeById(request.id())
                .setName(request.name());
        repository.save(productType);
    }

    @Override
    public void deleteProductTypeById(Long id) {
        ProductType productType = getProductTypeById(id);
        repository.delete(productType);
    }
}
