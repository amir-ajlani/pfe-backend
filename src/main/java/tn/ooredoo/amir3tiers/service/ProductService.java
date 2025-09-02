package tn.ooredoo.amir3tiers.service;

import tn.ooredoo.amir3tiers.DTOS.ProductRequest;
import tn.ooredoo.amir3tiers.DTOS.ProductResponse;
import tn.ooredoo.amir3tiers.enums.ProductType;

import java.util.List;

public interface ProductService {
    public List<ProductResponse> getAll();
    public ProductResponse getById(String code);
    public ProductResponse create(ProductRequest request);
    public void delete(String code);
    public List<ProductResponse> findByType(ProductType type);
}
