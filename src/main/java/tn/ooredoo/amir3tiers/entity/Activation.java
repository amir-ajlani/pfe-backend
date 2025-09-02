package tn.ooredoo.amir3tiers.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import tn.ooredoo.amir3tiers.entity.PointOfSale;
import tn.ooredoo.amir3tiers.entity.Customer;
import tn.ooredoo.amir3tiers.enums.ActivationStatus;

import java.sql.Blob;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "activation")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tech_type")
@Getter @Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public abstract class Activation {

    @Id @GeneratedValue
    private UUID id;
    private String iccid;
    private LocalDateTime activationDate;

    @Enumerated(EnumType.STRING)
    private ActivationStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POINT_OF_SALE_CODE")
    private PointOfSale pointOfSale;

    @Lob
    private byte[] fileBlobIdentiteV;

    @Lob
    private byte[] fileBlobIdentiteR;

    private String typeFileBlobIdentite;

    private String msisdnVoieIp;
    private String idBscsTransaction; // future usage
    private String phoneNumber;

    private String city;
    private String governorate;
    private String delegation;
    private String kitCode;
    private Integer snCodeCpe;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "tmCode", referencedColumnName = "tmcode"),
            @JoinColumn(name = "snCode", referencedColumnName = "sncode")
    })
    private ParamService paramService;
}