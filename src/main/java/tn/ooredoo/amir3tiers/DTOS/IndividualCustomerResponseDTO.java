package tn.ooredoo.amir3tiers.DTOS;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record IndividualCustomerResponseDTO(
        UUID id,
        String msisdn,
        LocalDateTime createdAt,
        Boolean blacklisted,
        String firstName,
        String lastName,
        LocalDate dateOfBirth,
        String idNumber
) {}