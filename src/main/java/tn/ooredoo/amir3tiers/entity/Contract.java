package tn.ooredoo.amir3tiers.entity;

import jakarta.persistence.*;
import lombok.*;
import tn.ooredoo.amir3tiers.enums.ContractStatus;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Contract {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(unique = true)
    private String contractNumber;

    private LocalDate startDate;
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    private ContractStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "activation_id")
    private Activation activation; // Peut être Box/Fiber/Fixe

    @Lob
    private byte[] contractPdfBlob;

    private String contractPdfMimeType;
}