package tn.ooredoo.amir3tiers.DTOS;

import java.util.UUID;

public record PartnerResponseDTO(
        Long id,
        String name,
        String phone,
        String email,
        String userUsername
) {}