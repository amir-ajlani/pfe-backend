package tn.ooredoo.amir3tiers.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter @Setter
public class FixeKit {
    @Id
    @Column(name = "KIT", length = 15)
    private String kit;

    @ManyToOne
    @JoinColumn(name = "COD_PROD", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "CD_PT_VENTE", nullable = false)
    private PointOfSale pointOfSale;

    @Embedded
    private KitStatus status;

    @Column(name = "DELIVERY_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deliveryDate;

    @Column(name = "ACCEPT_PDV_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date acceptPdvDate;
}