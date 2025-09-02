package tn.ooredoo.amir3tiers.DTOS;

import java.util.UUID;

public record PartnerRequestDTO(
        String name,
        String phone,
        String email,
        String userUsername,
        String password
) {}