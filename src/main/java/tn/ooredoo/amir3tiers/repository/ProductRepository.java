package tn.ooredoo.amir3tiers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.ooredoo.amir3tiers.entity.Product;
import tn.ooredoo.amir3tiers.enums.ProductType;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,String> {
    List<Product> findByType(ProductType type);
}
