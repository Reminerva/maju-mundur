package com.majumundur.majumundur.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.majumundur.majumundur.constant.DbBash;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = DbBash.TRANSACTION_DETAIL_DB)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "price")
    private Double price;

    @Column(name = "qty")
    private Integer qty;

    @ManyToOne
    @JoinColumn(name = "transaction_id")
    @JsonBackReference
    private Transaction transaction;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonBackReference
    private Product product;
}
