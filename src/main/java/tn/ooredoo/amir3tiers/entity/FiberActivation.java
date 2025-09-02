package tn.ooredoo.amir3tiers.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import tn.ooredoo.amir3tiers.entity.Activation;

@Entity
@DiscriminatorValue("FIBER")
@Getter @Setter
@SuperBuilder
@NoArgsConstructor
public class FiberActivation extends Activation {
    private String ontSerialNumber;
}