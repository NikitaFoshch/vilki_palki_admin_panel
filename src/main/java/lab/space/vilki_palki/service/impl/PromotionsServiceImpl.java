package lab.space.vilki_palki.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lab.space.vilki_palki.entity.Promotion;
import lab.space.vilki_palki.mapper.PromotionMapper;
import lab.space.vilki_palki.model.promotion.PromotionResponse;
import lab.space.vilki_palki.model.promotion.PromotionSaveRequest;
import lab.space.vilki_palki.model.promotion.PromotionUpdateRequest;
import lab.space.vilki_palki.repository.PromotionRepository;
import lab.space.vilki_palki.service.PromotionService;
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
public class PromotionsServiceImpl implements PromotionService {
    private final PromotionRepository promotionRepository;
    private final PromotionMapper promotionMapper;

    @Override
    public List<PromotionResponse> getAllPromotionsByOrderByCreateAt() {
        return promotionRepository.findAll(Sort.by(Sort.Direction.DESC, "createAt"))
                .stream().
                map(promotionMapper::toDto)
                .toList();
    }

    @Override
    public Promotion getPromotionById(Long id) {
        return promotionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Promotion not found with id " + id));
    }

    @Override
    public PromotionResponse getPromotionDto(Long id) {
        return promotionMapper.toDto(getPromotionById(id));
    }

    @Override
    public void savePromotion(PromotionSaveRequest request) {
        Promotion promotion = new Promotion()
                .setPercent(request.percent())
                .setProducts(request.products())
                .setName(request.name());
        if (nonNull(request.image())
                && nonNull(request.image().getOriginalFilename())
                && !request.image().getOriginalFilename().equals("")) {
            final String newFileName = UUID.randomUUID() + request.image().getOriginalFilename();
            FileUtil.saveFile(newFileName, request.image());
            FileUtil.deleteFile(promotion.getImage());
            promotion.setImage(newFileName);
        }
        promotionRepository.save(promotion);
    }

    @Override
    public void updatePromotionById(PromotionUpdateRequest request) {
        Promotion promotion = getPromotionById(request.id())
                .setPercent(request.percent())
                .setProducts(request.products())
                .setName(request.name());
        if (nonNull(request.image())
                && nonNull(request.image().getOriginalFilename())
                && !request.image().getOriginalFilename().equals("")) {
            final String newFileName = UUID.randomUUID() + request.image().getOriginalFilename();
            FileUtil.saveFile(newFileName, request.image());
            FileUtil.deleteFile(promotion.getImage());
            promotion.setImage(newFileName);
        }
        promotionRepository.save(promotion);
    }

    @Override
    public void deletePromotion(Long id) {
        Promotion promotion = getPromotionById(id);
        if (nonNull(promotion.getImage())) FileUtil.deleteFile(promotion.getImage());
        promotionRepository.delete(promotion);
    }
}
