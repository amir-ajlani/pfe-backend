package tn.ooredoo.amir3tiers.DTOS;

import java.util.Date;
import java.util.UUID;

public record PointOfSaleResponseDTO(
        String pointOfSaleCode,
        String pointOfSaleName,
        String address,
        String city,
        String telephone,
        String saleType,
        String status,
        Date statusDate,
        Date creationDate,
        Long responsableId,
        String responsableName,
        String governorateCode,
        String governorateName
) {}