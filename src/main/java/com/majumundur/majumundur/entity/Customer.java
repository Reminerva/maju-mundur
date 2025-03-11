package com.majumundur.majumundur.entity;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.majumundur.majumundur.constant.DbBash;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = DbBash.CUSTOMER_DB)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name")
    private String lastName;
    
    @Column(name = "date_of_birth")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dateOfBirth;
    
    @Column(name = "phone", unique = true)
    private String phone;

    @Column(name = "status")
    private String status;
    
    @OneToOne(cascade=CascadeType.PERSIST)
    @JsonBackReference
    private AppUser user;

    @OneToMany(mappedBy="customer")
    @JsonManagedReference
    @JsonIgnore
    private List<Transaction> transaction;

}    

