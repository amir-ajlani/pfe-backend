package tn.ooredoo.amir3tiers.DTOS;

public record UserAccountRequestDTO(
        String username,
        String password,
        String email,
        String userType,
        String status
) {}
