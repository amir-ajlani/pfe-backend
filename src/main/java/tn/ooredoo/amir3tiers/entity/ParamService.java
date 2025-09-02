package tn.ooredoo.amir3tiers.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.ooredoo.amir3tiers.enums.OfferType;

import java.math.BigDecimal;

@Entity
@Table(name = "PARAM_SERVICE")
@Getter
@Setter
@NoArgsConstructor
public class ParamService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PACK")
    private Integer idPack;
    @Enumerated(EnumType.STRING)
    private OfferType type;
    private String offer;
    private String tmcode;
    private String sncode;
    private BigDecimal price;
    private String debit;
}
