package tn.ooredoo.amir3tiers.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "customer_type")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class Customer {
    @Id @GeneratedValue
    private UUID id;

    @Column(unique = true)
    private String msisdn;

    private LocalDateTime createdAt = LocalDateTime.now();
    private Boolean blacklisted = false;
}
