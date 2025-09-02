package tn.ooredoo.amir3tiers.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "USER_ACCOUNT")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserAccount {

    @Id
    @Column(name = "USERNAME", length = 50)
    private String username;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "USER_TYPE") // PARTNER, BO
    private String userType;

    @Column(name = "STATUS") // ACTIVE, SUSPENDED
    private String status;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_AT")
    private Date createdAt;
}