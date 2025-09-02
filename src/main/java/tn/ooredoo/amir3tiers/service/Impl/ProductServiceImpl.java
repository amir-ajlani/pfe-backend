package tn.ooredoo.amir3tiers.service.Impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.ooredoo.amir3tiers.DTOS.ProductRequest;
import tn.ooredoo.amir3tiers.DTOS.ProductResponse;
import tn.ooredoo.amir3tiers.Mappers.ProductMapper;
import tn.ooredoo.amir3tiers.entity.Product;
import tn.ooredoo.amir3tiers.enums.ProductType;
import tn.ooredoo.amir3tiers.repository.ProductRepository;
import tn.ooredoo.amir3tiers.service.ProductService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public List<ProductResponse> getAll() {
        return productRepository.findAll().stream()
                .map(productMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponse getById(String code) {
        Product product = productRepository.findById(code)
                .orElseThrow(() -> new EntityNotFoundException("Produit introuvable"));
        return productMapper.toResponse(product);
    }

    @Override
    public ProductResponse create(ProductRequest request) {
        if (productRepository.existsById(request.code())) {
            throw new IllegalArgumentException("Code produit déjà utilisé");
        }
        Product saved = productRepository.save(productMapper.toEntity(request));
        return productMapper.toResponse(saved);
    }

    @Override
    public void delete(String code) {
        productRepository.deleteById(code);
    }

    @Override
    public List<ProductResponse> findByType(ProductType type) {
        return productRepository.findByType(type).stream()
                .map(productMapper::toResponse)
                .collect(Collectors.toList());
    }
}
