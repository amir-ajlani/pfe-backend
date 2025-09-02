package tn.ooredoo.amir3tiers.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@DiscriminatorValue("INDIVIDUAL")
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class IndividualCustomer extends Customer {
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String idNumber;

    @Builder
    public IndividualCustomer(UUID id, String msisdn, LocalDateTime createdAt, Boolean blacklist, String firstName, String lastName, LocalDate dateOfBirth, String idNumber) {
        super(id, msisdn, createdAt, blacklist);
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.idNumber = idNumber;
    }
}