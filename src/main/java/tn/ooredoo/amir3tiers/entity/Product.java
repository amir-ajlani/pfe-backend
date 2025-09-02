package tn.ooredoo.amir3tiers.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import tn.ooredoo.amir3tiers.enums.ProductType;

@Entity
@Getter
@Setter
public class Product {
    @Id
    @Column(name = "CODE", length = 10)
    private String code;

    @Column(name = "NAME", nullable = false)
    private String name;

    ProductType type;
}