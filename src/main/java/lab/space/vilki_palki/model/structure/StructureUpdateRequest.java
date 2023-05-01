package lab.space.vilki_palki.model.structure;

import org.springframework.web.multipart.MultipartFile;

public record StructureUpdateRequest(
        Long id,
        String name,
        Long structureCategory,
        Integer weight,
        Integer price,
        MultipartFile image
) {
}
