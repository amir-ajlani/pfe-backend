package tn.ooredoo.amir3tiers.DTOS;

import tn.ooredoo.amir3tiers.enums.OfferType;

import java.math.BigDecimal;

public record ParamServiceResponseDTO(
        Integer id,
        String tmCode,
        String snCode,
        String offre,
        OfferType offerType,
        BigDecimal price,
        String debit
) {}