package tn.ooredoo.amir3tiers.DTOS;

public record FiberActivationRequest(
        ActivationRequest base,
        String ontSerialNumber
) {}