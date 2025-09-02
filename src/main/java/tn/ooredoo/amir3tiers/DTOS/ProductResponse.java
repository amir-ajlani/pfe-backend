package tn.ooredoo.amir3tiers.DTOS;

import lombok.Builder;
import tn.ooredoo.amir3tiers.enums.ProductType;

@Builder
public record ProductResponse(
        String code,
        String name,
        ProductType type
) {}