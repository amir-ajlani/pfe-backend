package tn.ooredoo.amir3tiers.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@DiscriminatorValue("BUSINESS")
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class BusinessCustomer extends Customer {
    private String businessName;
    private String tradeRegisterNumber;;

    @Builder
    public BusinessCustomer(UUID id, String msisdn, String status, LocalDateTime createdAt, Boolean blacklist, String businessName, String tradeRegisterNumber) {
        super(id, msisdn, createdAt, blacklist);
        this.businessName = businessName;
        this.tradeRegisterNumber=tradeRegisterNumber;
    }
}
