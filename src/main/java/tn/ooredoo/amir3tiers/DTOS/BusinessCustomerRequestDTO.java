package tn.ooredoo.amir3tiers.DTOS;

public record BusinessCustomerRequestDTO(
        String msisdn,
        String businessName,
        String tradeRegisterNumber
) {}