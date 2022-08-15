package com.dev.basic.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private long userId;
    private String userName;
    private String mail;
    private String password;
    @OneToOne
    UserPrivacyAgreement userPrivacyAgreement;
}
