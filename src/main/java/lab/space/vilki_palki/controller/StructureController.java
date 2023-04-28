package lab.space.vilki_palki.controller;

import groovyjarjarantlr4.v4.codegen.model.Wildcard;
import lab.space.vilki_palki.entity.Structure;
import lab.space.vilki_palki.entity.StructureCategory;
import lab.space.vilki_palki.model.*;
import lab.space.vilki_palki.service.StructureService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
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

    @DeleteMapping("delete-user/{id}")
    public String deleteStructure(@PathVariable Long id) {
        structureService.deleteStructureById(id);
        return "redirect:/structures";
    }

    @PostMapping("structure-save")
    public ResponseEntity<?> saveStructure(@ModelAttribute StructureSaveRequest request) {
        structureService.saveStructure(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("structure-update")
    public ResponseEntity<?> updateStructure(@ModelAttribute StructureUpdateRequest request) {
//        structureService.updateStructure(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("get-all-structure")
    public ResponseEntity<Page<StructureResponse>> getAllStructures(@RequestBody StructureRequest structureRequest) {
        return ResponseEntity.ok(structureService.getAllProductStructuresByOrderByCreateAt(structureRequest));
    }

    @PostMapping("get-structure-by-id")
    public ResponseEntity<StructureResponse> getStructureById(@RequestBody Long id) {
        return ResponseEntity.ok(structureService.getStructureById(id));
    }
}
