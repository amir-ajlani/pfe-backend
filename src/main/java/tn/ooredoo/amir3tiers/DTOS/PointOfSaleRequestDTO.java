package tn.ooredoo.amir3tiers.DTOS;

import java.util.UUID;

public record PointOfSaleRequestDTO(
        String pointOfSaleCode,
        String pointOfSaleName,
        String address,
        String city,
        String telephone,
        String saleType,
        String status,
        Long responsableId,
        String governorateCode
) {}