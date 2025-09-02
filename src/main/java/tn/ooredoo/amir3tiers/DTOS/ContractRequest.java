package tn.ooredoo.amir3tiers.DTOS;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import tn.ooredoo.amir3tiers.enums.ContractStatus;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record ContractRequest(
        String contractNumber,
        LocalDate startDate,
        LocalDate endDate,
        ContractStatus status,
        UUID customerId,
        UUID activationId
) {}