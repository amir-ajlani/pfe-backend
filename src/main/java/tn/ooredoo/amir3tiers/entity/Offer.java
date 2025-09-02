package tn.ooredoo.amir3tiers.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import tn.ooredoo.amir3tiers.enums.OfferType;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Offer {
    @Id
    @GeneratedValue
    private UUID id;
    private String code;
    private String name;
    @Enumerated(EnumType.STRING)
    private OfferType type;
    private BigDecimal price;
    private Integer validityMonths;
}