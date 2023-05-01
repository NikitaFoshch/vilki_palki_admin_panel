package lab.space.vilki_palki.controller;

import lab.space.vilki_palki.model.structure.StructureRequest;
import lab.space.vilki_palki.model.structure.StructureResponse;
import lab.space.vilki_palki.model.structure.StructureSaveRequest;
import lab.space.vilki_palki.model.structure.StructureUpdateRequest;
import lab.space.vilki_palki.service.StructureService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("structures")
@RequiredArgsConstructor
public class StructureController {
    private final StructureService structureService;

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
    public ResponseEntity<?> saveStructure(@ModelAttribute StructureSaveRequest request) {
        structureService.saveStructure(request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("structure-update")
    public ResponseEntity<?> updateStructure(@ModelAttribute StructureUpdateRequest request) {
        structureService.updateStructure(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("get-all-structure")
    public ResponseEntity<Page<StructureResponse>> getAllStructures(@RequestBody StructureRequest structureRequest) {
        return ResponseEntity.ok(structureService.getAllProductStructuresByOrderByCreateAt(structureRequest));
    }

    @GetMapping("get-structure/{id}")
    public ResponseEntity<StructureResponse> getStructureById(@PathVariable Long id) {
        return ResponseEntity.ok(structureService.getStructureById(id));
    }
}
