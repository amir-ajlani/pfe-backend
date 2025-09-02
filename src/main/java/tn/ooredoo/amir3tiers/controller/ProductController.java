package tn.ooredoo.amir3tiers.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.ooredoo.amir3tiers.DTOS.ProductRequest;
import tn.ooredoo.amir3tiers.DTOS.ProductResponse;
import tn.ooredoo.amir3tiers.enums.ProductType;
import tn.ooredoo.amir3tiers.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public List<ProductResponse> getAll() {
        return productService.getAll();
    }

    @GetMapping("/{code}")
    public ProductResponse getById(@PathVariable(name = "code") String code) {
        return productService.getById(code);
    }

    @PostMapping
    public ProductResponse create(@RequestBody @Valid ProductRequest request) {
        return productService.create(request);
    }

    @DeleteMapping("/{code}")
    public void delete(@PathVariable(name = "code") String code) {
        productService.delete(code);
    }

    @GetMapping("/by-type")
    public List<ProductResponse> getByType(@RequestParam(name = "type") ProductType type) {
        return productService.findByType(type);
    }
}