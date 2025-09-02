package tn.ooredoo.amir3tiers.DTOS;

public record LoginResponseDTO(
        String token,
        String userType,
        String username
) {}