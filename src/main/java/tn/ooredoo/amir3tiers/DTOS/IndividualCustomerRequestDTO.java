package tn.ooredoo.amir3tiers.DTOS;

import java.time.LocalDate;

public record IndividualCustomerRequestDTO(
        String msisdn,
        String firstName,
        String lastName,
        LocalDate dateOfBirth,
        String idNumber
) {}