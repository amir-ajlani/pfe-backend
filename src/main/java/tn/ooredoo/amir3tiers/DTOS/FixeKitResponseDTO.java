package tn.ooredoo.amir3tiers.DTOS;

import java.util.Date;

public record FixeKitResponseDTO(
        String kit,
        String pointOfSaleCode,
        String pointOfSaleName,
        String productCode,
        String productName,
        String status,
        Date statusDate,
        Date deliveryDate,
        Date acceptPdvDate
) {}