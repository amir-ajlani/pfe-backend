package tn.ooredoo.amir3tiers.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "GOVERNORATE")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Governorate {
    @Id
    @Column(name = "CODE")
    private String code;

    @Column(name = "NAME", nullable = false)
    private String name;
}