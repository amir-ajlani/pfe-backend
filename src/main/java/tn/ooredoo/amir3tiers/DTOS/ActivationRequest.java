package tn.ooredoo.amir3tiers.DTOS;

import tn.ooredoo.amir3tiers.enums.ActivationStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record ActivationRequest(
        String iccid,
        LocalDateTime activationDate,
        ActivationStatus status,
        UUID customerId,
        String pointOfSaleCode,
        byte[] fileBlobIdentiteV,
        byte[] fileBlobIdentiteR,
        String typeFileBlobIdentite,
        String msisdnVoieIp,
        String idBscsTransaction,
        String phoneNumber,
        String city,
        String governorate,
        String delegation,
        String kitCode,
        Integer snCodeCpe,
        String tmCode,
        String snCode
) {}
