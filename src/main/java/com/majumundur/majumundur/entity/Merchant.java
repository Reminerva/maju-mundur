package com.majumundur.majumundur.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.majumundur.majumundur.constant.DbBash;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = DbBash.MERCHANT_DB)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Merchant {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "merchant_name", nullable = false)
    private String merchantName;
    
    @Column(name = "phone", unique = true)
    private String phone;

    @Column(name = "location")
    private String location;
    
    @OneToOne(cascade=CascadeType.PERSIST)
    @JsonBackReference
    private AppUser user;
    
    @OneToMany(mappedBy="merchant")
    @JsonBackReference
    private List<Product> product;

}    

