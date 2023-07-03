package lab.space.vilki_palki.controller;

import lab.space.vilki_palki.model.product_type.ProductTypeRequest;
import lab.space.vilki_palki.model.product_type.ProductTypeResponse;
import lab.space.vilki_palki.model.product_type.ProductTypeSaveRequest;
import lab.space.vilki_palki.model.product_type.ProductTypeUpdateRequest;
import lab.space.vilki_palki.service.ProductTypeService;
import lab.space.vilki_palki.util.ErrorMapper;
import lab.space.vilki_palki.validator.ProductTypeValidation;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("product-types")
@AllArgsConstructor
public class ProductTypesController {
    private final ProductTypeService productTypeService;
    private final ProductTypeValidation productTypeValidation;

    @GetMapping({"/", ""})
    public String showProductTypesPage() {
        return "/admin-panel/pages/product-structure/product-types";
    }

    @DeleteMapping("delete-product-type/{id}")
    public ResponseEntity<?> deleteProductType(@PathVariable Long id) {
        productTypeService.deleteProductTypeById(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("product-type-save")
    @ResponseBody
    public ResponseEntity<?> saveProductType(@Valid @RequestBody ProductTypeSaveRequest request,
                                             BindingResult bindingResult) {
        productTypeValidation.isNameUniqueValidation(request.getName(), bindingResult);
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(ErrorMapper.mapErrors(bindingResult));
        }
        productTypeService.saveProductType(request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("product-type-update")
    @ResponseBody
    public ResponseEntity<?> updateProductType(@Valid @RequestBody ProductTypeUpdateRequest request,
                                               BindingResult bindingResult) {
        productTypeValidation.isNameUniqueValidationWithId(request.getId(), request.getName(), bindingResult);
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(ErrorMapper.mapErrors(bindingResult));
        }
        productTypeService.updateProductType(request);
        return ResponseEntity.ok().build();
    }


    @PostMapping("get-all-product-types")
    public ResponseEntity<Page<ProductTypeResponse>> getAllProductTypes(@RequestBody ProductTypeRequest request) {
        return ResponseEntity.ok(productTypeService.getAllProductTypesByOrderByCreateAt(request));
    }

    @GetMapping("get-product-type/{id}")
    public ResponseEntity<ProductTypeResponse> getProductTypeById(@PathVariable Long id) {
        return ResponseEntity.ok(productTypeService.getProductTypeToDto(id));
    }
}
