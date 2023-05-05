package lab.space.vilki_palki.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lab.space.vilki_palki.entity.ProductType;
import lab.space.vilki_palki.mapper.ProductTypeMapper;
import lab.space.vilki_palki.model.product_type.ProductTypeResponse;
import lab.space.vilki_palki.repository.ProductTypeRepository;
import lab.space.vilki_palki.service.ProductTypeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ProductTypeServiceImpl implements ProductTypeService {
    private final ProductTypeRepository repository;

    @Override
    public ProductType getProductTypeById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ProductType not found by id " + id));
    }

    @Override
    public ProductTypeResponse getProductTypeToDto(Long id) {
        return  ProductTypeMapper.toDto(getProductTypeById(id));
    }

    @Override
    public List<ProductTypeResponse> getAllProductTypes() {
        return repository.findAll(Sort.by(Sort.Direction.ASC,"name"))
                .stream()
                .map(ProductTypeMapper::toDto)
                .toList();
    }
}
