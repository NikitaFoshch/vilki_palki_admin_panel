package lab.space.vilki_palki.model.structure;

import lab.space.vilki_palki.entity.StructureCategory;
import org.springframework.web.multipart.MultipartFile;

public record StructureSaveRequest(
        String name,
        Long structureCategoryId,
        Integer weight,
        Integer price,
        MultipartFile image
){}
