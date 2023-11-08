package com.example.springboojpacrud.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Table(name = "db_user")
@Entity

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column(unique = true)
    private String username;
    private String password;
    private String fullname;
    private Date dob;
    private String address;
    private String hometown;
    @Column(unique = true)
    private String phoneNumber;
    @Column(unique = true)
    private String email;
    private String role;

}
