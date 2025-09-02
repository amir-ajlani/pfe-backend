package tn.ooredoo.amir3tiers.DTOS;

import tn.ooredoo.amir3tiers.enums.ActivationStatus;
import tn.ooredoo.amir3tiers.enums.OfferType;

import java.time.LocalDateTime;
import java.util.UUID;

public record ActivationResponse(
        UUID id,
        String iccid,
        LocalDateTime activationDate,
        ActivationStatus status,
        UUID customerId,
        String customerName,
        String pointOfSaleCode,
        String pointOfSaleName,
        String msisdnVoieIp,
        String phoneNumber,
        String kitCode,
        String governorate,
        String city,
        String delegation,
        Integer snCodeCpe,
        String offre,
        OfferType type,
        String tmCode,
        String snCode
) {}
