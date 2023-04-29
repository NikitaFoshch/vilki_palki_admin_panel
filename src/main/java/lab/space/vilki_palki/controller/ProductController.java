package lab.space.vilki_palki.controller;

import lab.space.vilki_palki.model.product.ProductResponse;
import lab.space.vilki_palki.model.product.ProductSaveRequest;
import lab.space.vilki_palki.model.product.ProductUpdateRequest;
import lab.space.vilki_palki.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping({"/", ""})
    public String showProductPage() {
        return "/admin-panel/pages/product-structure/products";
    }

    @PostMapping("product-save")
    public ResponseEntity<?> saveBanner(@ModelAttribute ProductSaveRequest request) {
        productService.saveProduct(request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("product-update")
    public ResponseEntity<?> updateBanner(@ModelAttribute ProductUpdateRequest request) {
        productService.updateProductById(request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("product-delete/{id}")
    public ResponseEntity<?> deleteBanner(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("get-all-products")
    public ResponseEntity<List<ProductResponse>> getAllBanners() {
        return ResponseEntity.ok(productService.getAllProductsByOrderByCreateAt());
    }

    @GetMapping("get-product/{id}")
    public ResponseEntity<ProductResponse> getBannerById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductDto(id));
    }
}
