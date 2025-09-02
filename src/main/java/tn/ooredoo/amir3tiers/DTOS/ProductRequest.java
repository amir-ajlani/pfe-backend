package tn.ooredoo.amir3tiers.DTOS;


import tn.ooredoo.amir3tiers.enums.ProductType;

public record ProductRequest(
        String code,
        String name,
        ProductType type
) {}