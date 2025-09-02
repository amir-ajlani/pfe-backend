package tn.ooredoo.amir3tiers.DTOS;

import tn.ooredoo.amir3tiers.enums.ContractStatus;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record ContractResponse(
        UUID id,
        String contractNumber,
        LocalDate startDate,
        LocalDate endDate,
        ContractStatus status,
        UUID customerId,
        UUID activationId,
        byte[] contractPdfBlob,
        String contractPdfMimeType
) {}