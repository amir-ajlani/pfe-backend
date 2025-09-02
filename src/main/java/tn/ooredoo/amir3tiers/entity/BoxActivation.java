package tn.ooredoo.amir3tiers.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import tn.ooredoo.amir3tiers.entity.Activation;

@Entity
@DiscriminatorValue("BOX")
@Getter @Setter
@SuperBuilder
@NoArgsConstructor
public class BoxActivation extends Activation {
    private String boxSerialNumber;
    private String adslLogin;
    private String adslPassword;
}