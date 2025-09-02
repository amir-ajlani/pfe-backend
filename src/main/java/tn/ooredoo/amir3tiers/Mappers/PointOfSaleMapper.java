package tn.ooredoo.amir3tiers.Mappers;

import org.springframework.stereotype.Component;
import tn.ooredoo.amir3tiers.DTOS.PointOfSaleRequestDTO;
import tn.ooredoo.amir3tiers.DTOS.PointOfSaleResponseDTO;
import tn.ooredoo.amir3tiers.entity.Governorate;
import tn.ooredoo.amir3tiers.entity.Partner;
import tn.ooredoo.amir3tiers.entity.PointOfSale;

import java.util.Date;

@Component
public class PointOfSaleMapper {

    public PointOfSale toEntity(PointOfSaleRequestDTO dto, Partner partner, Governorate governorate) {

        return PointOfSale.builder()
                .pointOfSaleCode(dto.pointOfSaleCode())
                .pointOfSaleName(dto.pointOfSaleName())
                .address(dto.address())
                .city(dto.city())
                .telephone(dto.telephone())
                .saleType(dto.saleType())
                .status(dto.status())
                .statusDate(new Date())
                .creationDate(new Date())
                .responsable(partner)
                .governorate(governorate)
                .build();
    }

    public void updateEntity(PointOfSale entity, PointOfSaleRequestDTO dto, Partner partner, Governorate governorate) {
        entity.setPointOfSaleName(dto.pointOfSaleName());
        entity.setAddress(dto.address());
        entity.setCity(dto.city());
        entity.setTelephone(dto.telephone());
        entity.setSaleType(dto.saleType());
        entity.setStatus(dto.status());
        entity.setStatusDate(new Date());
        entity.setResponsable(partner);
        entity.setGovernorate(governorate);
    }

    public PointOfSaleResponseDTO toResponse(PointOfSale entity) {
        return new PointOfSaleResponseDTO(
                entity.getPointOfSaleCode(),
                entity.getPointOfSaleName(),
                entity.getAddress(),
                entity.getCity(),
                entity.getTelephone(),
                entity.getSaleType(),
                entity.getStatus(),
                entity.getStatusDate(),
                entity.getCreationDate(),
                entity.getResponsable() != null ? entity.getResponsable().getId() : null,
                entity.getResponsable() != null ? entity.getResponsable().getName() : null,
                entity.getGovernorate() != null ? entity.getGovernorate().getCode() : null,
                entity.getGovernorate() != null ? entity.getGovernorate().getName() : null
        );
    }
}