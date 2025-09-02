package tn.ooredoo.amir3tiers.DTOS;

public record FixeActivationResponse(
        ActivationResponse base,
        String activationType
) {}