package tn.ooredoo.amir3tiers.DTOS;

import tn.ooredoo.amir3tiers.enums.OfferType;

import java.math.BigDecimal;

public record ParamServiceDto(
        OfferType type,
        String offre,
        String tmcode,
        String sncode,
        BigDecimal prix,
        String debit
) {}