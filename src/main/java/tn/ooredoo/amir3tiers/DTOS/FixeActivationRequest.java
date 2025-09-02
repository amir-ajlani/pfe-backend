package tn.ooredoo.amir3tiers.DTOS;

public record FixeActivationRequest(
        ActivationRequest base,
        String activationType // "indoor" or "outdoor"
) {}