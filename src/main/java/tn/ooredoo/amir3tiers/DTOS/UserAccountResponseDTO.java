package tn.ooredoo.amir3tiers.DTOS;

import java.util.Date;
import java.util.UUID;

public record UserAccountResponseDTO(
        String username,
        String email,
        String userType,
        String status,
        Date createdAt
) {}