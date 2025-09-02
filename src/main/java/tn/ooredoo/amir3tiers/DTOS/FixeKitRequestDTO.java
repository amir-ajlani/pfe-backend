package tn.ooredoo.amir3tiers.DTOS;

import java.util.Date;

public record FixeKitRequestDTO(
        String kit,
        String pointOfSaleCode,
        String productCode,
        String status,
        Date statusDate,
        Date deliveryDate,
        Date acceptPdvDate
) {}
