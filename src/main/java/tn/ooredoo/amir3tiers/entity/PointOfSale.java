package tn.ooredoo.amir3tiers.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "POINT_OF_SALE")
public class PointOfSale {

    @Id
    @Column(name = "POINT_OF_SALE_CODE", length = 15)
    private String pointOfSaleCode;

    @Column(name = "POINT_OF_SALE_NAME")
    private String pointOfSaleName;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "CITY")
    private String city;

    @Column(name = "TELEPHONE")
    private String telephone;

    @OneToOne
    @JoinColumn(name = "PARTNER_ID")
    private Partner responsable;

    @Column(name = "SALE_TYPE")
    private String saleType;

    @Column(name = "STATUS")
    private String status; // A: active, S: suspended

    @Column(name = "STATUS_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date statusDate;

    @Column(name = "CREATION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    @ManyToOne
    @JoinColumn(name = "GOVERNORATE_CODE")
    private Governorate governorate;
}