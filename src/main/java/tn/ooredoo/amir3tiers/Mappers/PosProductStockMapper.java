package tn.ooredoo.amir3tiers.Mappers;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import tn.ooredoo.amir3tiers.DTOS.FixeKitRequestDTO;
import tn.ooredoo.amir3tiers.DTOS.FixeKitResponseDTO;
import tn.ooredoo.amir3tiers.entity.FixeKit;
import tn.ooredoo.amir3tiers.entity.PointOfSale;
import tn.ooredoo.amir3tiers.entity.Product;
import tn.ooredoo.amir3tiers.repository.PointOfSaleRepository;
import tn.ooredoo.amir3tiers.repository.ProductRepository;

@Component
@RequiredArgsConstructor
public class PosProductStockMapper {

    private final ModelMapper modelMapper;
    private final PointOfSaleRepository pointOfSaleRepository;
    private final ProductRepository productRepository;

    public FixeKit toEntity(FixeKitRequestDTO request) {
        FixeKit entity = new FixeKit();

        // Gestion des relations
        PointOfSale pos = pointOfSaleRepository.findById(request.pointOfSaleCode())
                .orElseThrow(() -> new IllegalArgumentException("PointOfSale not found: " + request.pointOfSaleCode()));
        Product product = productRepository.findById(request.productCode())
                .orElseThrow(() -> new IllegalArgumentException("Product not found: " + request.productCode()));

        entity.setPointOfSale(pos);
        entity.setProduct(product);

        return entity;
    }

    public FixeKitResponseDTO toResponse(FixeKit entity) {
        return new FixeKitResponseDTO(
                entity.getKit(),
                entity.getPointOfSale().getPointOfSaleCode(),
                entity.getPointOfSale().getPointOfSaleName(),
                entity.getProduct().getCode(),
                entity.getProduct().getName(),
                entity.getStatus().getValue(),
                entity.getStatus().getDate(),
                entity.getDeliveryDate(),
                entity.getAcceptPdvDate()
        );
    }

    // Optionnel : mise à jour existante
    public void updateEntity(FixeKit entity, FixeKitRequestDTO request) {
        if (!entity.getPointOfSale().getPointOfSaleCode().equals(request.pointOfSaleCode())) {
            PointOfSale newPos = pointOfSaleRepository.findById(request.pointOfSaleCode())
                    .orElseThrow(() -> new IllegalArgumentException("PointOfSale not found"));
            entity.setPointOfSale(newPos);
        }

        if (!entity.getProduct().getCode().equals(request.productCode())) {
            Product newProduct = productRepository.findById(request.productCode())
                    .orElseThrow(() -> new IllegalArgumentException("Product not found"));
            entity.setProduct(newProduct);
        }
    }
}
