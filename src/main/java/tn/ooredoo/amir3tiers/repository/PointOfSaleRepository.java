package tn.ooredoo.amir3tiers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.ooredoo.amir3tiers.entity.PointOfSale;

import java.util.List;
import java.util.UUID;

public interface PointOfSaleRepository extends JpaRepository<PointOfSale, String> {
    List<PointOfSale> findByResponsable_Id(Long partnerId);
    long countByGovernorate_Code(String governorateCode);
}