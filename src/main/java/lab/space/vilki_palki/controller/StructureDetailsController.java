package lab.space.vilki_palki.controller;

import javax.validation.Valid;
import lab.space.vilki_palki.model.structure_category.StructureCategoryRequest;
import lab.space.vilki_palki.model.structure_category.StructureCategoryResponse;
import lab.space.vilki_palki.model.structure_category.StructureCategorySaveRequest;
import lab.space.vilki_palki.model.structure_category.StructureCategoryUpdateRequest;
import lab.space.vilki_palki.service.StructureCategoryService;
import lab.space.vilki_palki.util.ErrorMapper;
import lab.space.vilki_palki.validator.StructureCategoryValidation;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("structure-details")
@AllArgsConstructor
public class StructureDetailsController {
    private final StructureCategoryService structureCategoryService;
    private final StructureCategoryValidation structureCategoryValidation;

    @GetMapping({"/", ""})
    public String showStructureDetailsPage() {
        return "/admin-panel/pages/product-structure/structure-details";
    }

    @DeleteMapping("delete-structure-category/{id}")
    public ResponseEntity<?> deleteStructureCategory(@PathVariable Long id) {
        structureCategoryService.deleteStructureCategoryById(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("structure-category-save")
    @ResponseBody
    public ResponseEntity<?> saveStructureCategory(@Valid @RequestBody StructureCategorySaveRequest request,
                                           BindingResult bindingResult) {
        structureCategoryValidation.isNameUniqueValidation(request.getName(), bindingResult);
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(ErrorMapper.mapErrors(bindingResult));
        }
        structureCategoryService.saveStructureCategory(request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("structure-category-update")
    @ResponseBody
    public ResponseEntity<?> updateStructureCategory(@Valid @RequestBody StructureCategoryUpdateRequest request,
                                             BindingResult bindingResult) {
        structureCategoryValidation.isNameUniqueValidationWithId(request.getId(), request.getName(), bindingResult);
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(ErrorMapper.mapErrors(bindingResult));
        }
        structureCategoryService.updateStructureCategory(request);
        return ResponseEntity.ok().build();
    }


    @PostMapping("get-all-structure-categories")
    public ResponseEntity<Page<StructureCategoryResponse>> getAllStructureCategories(@RequestBody StructureCategoryRequest request) {
        return ResponseEntity.ok(structureCategoryService.getAllStructureCategoriesByOrderByCreateAt(request));
    }

    @GetMapping("get-structure-category/{id}")
    public ResponseEntity<StructureCategoryResponse> getStructureById(@PathVariable Long id) {
        return ResponseEntity.ok(structureCategoryService.getStructureCategoryToDto(id));
    }
}
