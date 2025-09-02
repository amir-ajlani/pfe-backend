package tn.ooredoo.amir3tiers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.ooredoo.amir3tiers.entity.FixeKit;

import java.util.List;

@Repository
public interface PosProductStockRepository extends JpaRepository<FixeKit, String> {

    List<FixeKit> findByProduct_Code(String productCode);
    List<FixeKit> findByProduct_CodeAndPointOfSale_PointOfSaleCode(String productCode, String pointOfSaleCode);
}