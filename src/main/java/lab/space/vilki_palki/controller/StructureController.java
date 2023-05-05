package lab.space.vilki_palki.controller;

import jakarta.validation.Valid;
import lab.space.vilki_palki.model.structure.StructureRequest;
import lab.space.vilki_palki.model.structure.StructureResponse;
import lab.space.vilki_palki.model.structure.StructureSaveRequest;
import lab.space.vilki_palki.model.structure.StructureUpdateRequest;
import lab.space.vilki_palki.model.structure_category.StructureCategoryResponse;
import lab.space.vilki_palki.service.StructureCategoryService;
import lab.space.vilki_palki.service.StructureService;
import lab.space.vilki_palki.util.ErrorMapper;
import lab.space.vilki_palki.validator.ImageValidation;
import lab.space.vilki_palki.validator.StructureValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("structures")
@RequiredArgsConstructor
public class StructureController {
    private final StructureService structureService;
    private final StructureCategoryService structureCategoryService;
    private final StructureValidator structureValidator;
    private final ImageValidation imageValidation;

    @GetMapping({"/", ""})
    public ModelAndView showStructurePage() {
        return new ModelAndView("/admin-panel/pages/product-structure/structures");
    }

    @DeleteMapping("delete-structure/{id}")
    public ResponseEntity<?> deleteStructure(@PathVariable Long id) {
        structureService.deleteStructureById(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("structure-save")
    @ResponseBody
    public ResponseEntity<?> saveStructure(@Valid @ModelAttribute StructureSaveRequest request,
                                           BindingResult bindingResult) {
        structureValidator.isNameUniqueValidation(request.name(), bindingResult);
        imageValidation.imageContentTypeValidation(request.image(), bindingResult);
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(ErrorMapper.mapErrors(bindingResult));
        }
        structureService.saveStructure(request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("structure-update")
    @ResponseBody
    public ResponseEntity<?> updateStructure(@Valid @ModelAttribute StructureUpdateRequest request,
                                             BindingResult bindingResult) {
        structureValidator.isNameUniqueValidationWithId(request.id(), request.name(), bindingResult);
        imageValidation.imageContentTypeValidation(request.image(), bindingResult);
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(ErrorMapper.mapErrors(bindingResult));
        }
        structureService.updateStructure(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("get-all-structure")
    public ResponseEntity<Page<StructureResponse>> getAllStructures(@RequestBody StructureRequest request) {
        return ResponseEntity.ok(structureService.getAllStructuresByOrderByCreateAt(request));
    }

    @GetMapping("get-all-structure-categories")
    public ResponseEntity<List<StructureCategoryResponse>> getAllStructureCategories() {
        return ResponseEntity.ok(structureCategoryService.getAllStructureCategories());
    }

    @GetMapping("get-structure/{id}")
    public ResponseEntity<StructureResponse> getStructureById(@PathVariable Long id) {
        return ResponseEntity.ok(structureService.getStructureDtoById(id));
    }
}
