package lab.space.vilki_palki.model;

import lab.space.vilki_palki.entity.StructureCategory;
import org.springframework.web.multipart.MultipartFile;

public record StructureSaveRequest(
        String title,
        Long structureCategoryId,
        Integer weight,
        Integer price,
        MultipartFile image
){}
