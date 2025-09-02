package tn.ooredoo.amir3tiers.DTOS;

public record FixeKitBatchCreateRequestDTO(
        String productCode,
        String pointOfSaleCode,
        String startKit,
        String endKit
) {}
