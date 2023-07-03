package lab.space.vilki_palki.controller;

import lab.space.vilki_palki.model.product_category.ProductCategoryRequest;
import lab.space.vilki_palki.model.product_category.ProductCategoryResponse;
import lab.space.vilki_palki.model.product_category.ProductCategorySaveRequest;
import lab.space.vilki_palki.model.product_category.ProductCategoryUpdateRequest;
import lab.space.vilki_palki.service.ProductCategoryService;
import lab.space.vilki_palki.util.ErrorMapper;
import lab.space.vilki_palki.validator.ImageValidation;
import lab.space.vilki_palki.validator.ProductCategoryValidation;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("product-categories")
@AllArgsConstructor
public class ProductDetailsController {
    private final ProductCategoryService productCategoryService;
    private final ProductCategoryValidation productCategoryValidation;
    private final ImageValidation imageValidation;

    @GetMapping({"/", ""})
    public String showProductDetailsPage() {
        return "/admin-panel/pages/product-structure/product-categories";
    }

    @DeleteMapping("delete-product-category/{id}")
    public ResponseEntity<?> deleteProductCategory(@PathVariable Long id) {
        productCategoryService.deleteProductCategoryById(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("product-category-save")
    @ResponseBody
    public ResponseEntity<?> saveProductCategory(@Valid @ModelAttribute ProductCategorySaveRequest request,
                                                 BindingResult bindingResult) {
        productCategoryValidation.isNameUniqueValidation(request.getName(), bindingResult);
        imageValidation.imageContentTypeValidation(request.getImage(), bindingResult);
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(ErrorMapper.mapErrors(bindingResult));
        }
        productCategoryService.saveProductCategory(request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("product-category-update")
    @ResponseBody
    public ResponseEntity<?> updateProductCategory(@Valid @ModelAttribute ProductCategoryUpdateRequest request,
                                                   BindingResult bindingResult) {
        productCategoryValidation.isNameUniqueValidationWithId(request.getId(), request.getName(), bindingResult);
        imageValidation.imageContentTypeValidation(request.getImage(), bindingResult);
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(ErrorMapper.mapErrors(bindingResult));
        }
        productCategoryService.updateProductCategory(request);
        return ResponseEntity.ok().build();
    }


    @PostMapping("get-all-product-categories")
    public ResponseEntity<Page<ProductCategoryResponse>> getAllProductCategories(@RequestBody ProductCategoryRequest request) {
        return ResponseEntity.ok(productCategoryService.getAllProductCategoriesByOrderByCreateAt(request));
    }

    @GetMapping("get-product-category/{id}")
    public ResponseEntity<ProductCategoryResponse> getProductCategoryById(@PathVariable Long id) {
        return ResponseEntity.ok(productCategoryService.getProductCategoryToDto(id));
    }

}
