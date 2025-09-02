package tn.ooredoo.amir3tiers.DTOS;

import java.time.LocalDateTime;
import java.util.UUID;

public record BusinessCustomerResponseDTO(
        UUID id,
        String msisdn,
        LocalDateTime createdAt,
        Boolean blacklisted,
        String businessName,
        String tradeRegisterNumber
) {}