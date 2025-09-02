package tn.ooredoo.amir3tiers.DTOS;

public record BoxActivationResponse(
        ActivationResponse base,
        String boxSerialNumber,
        String adslLogin,
        String adslPassword
) {}