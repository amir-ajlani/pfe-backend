package tn.ooredoo.amir3tiers.DTOS;

public record FiberActivationResponse(
        ActivationResponse base,
        String ontSerialNumber
) {}