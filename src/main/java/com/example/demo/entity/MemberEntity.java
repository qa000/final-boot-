package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "members")
public class MemberEntity {

    @Id
    @Column(name = "id", nullable = false, unique = true, length = 255)
    private String id;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "birth", nullable = false)
    private String birth;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "address")
    private String address;

    @Lob
    @Column(name = "profile", columnDefinition = "LONGBLOB")
    private byte[] profile;

    @Column(name = "profile_name")
    private String profileName;
}
