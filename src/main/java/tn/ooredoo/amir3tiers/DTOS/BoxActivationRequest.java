package tn.ooredoo.amir3tiers.DTOS;

public record BoxActivationRequest(
        ActivationRequest base,
        String boxSerialNumber,
        String adslLogin,
        String adslPassword
) {}