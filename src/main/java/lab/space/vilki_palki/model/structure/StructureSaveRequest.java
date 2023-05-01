package lab.space.vilki_palki.model.structure;

import org.springframework.web.multipart.MultipartFile;

public record StructureSaveRequest(
        String name,
        Long structureCategoryId,
        Integer weight,
        Integer price,
        MultipartFile image
) {
}
