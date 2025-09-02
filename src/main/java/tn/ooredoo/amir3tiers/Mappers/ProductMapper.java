package tn.ooredoo.amir3tiers.Mappers;

import org.springframework.stereotype.Component;
import tn.ooredoo.amir3tiers.DTOS.ProductRequest;
import tn.ooredoo.amir3tiers.DTOS.ProductResponse;
import tn.ooredoo.amir3tiers.entity.Product;

@Component
public class ProductMapper {
    public Product toEntity(ProductRequest request) {
        Product product = new Product();
        product.setCode(request.code());
        product.setName(request.name());
        product.setType(request.type());
        return product;
    }

    public ProductResponse toResponse(Product product) {
        return ProductResponse.builder()
                .code(product.getCode())
                .name(product.getName())
                .type(product.getType())
                .build();
    }
}