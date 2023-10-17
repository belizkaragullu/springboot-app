package com.projects.Product.dbmodel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name="PRODUCT")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Product {

    @Id
    @Column(name = "ID", nullable = false)
    private long productId;

    @Column(name = "NAME", nullable = false) //baska bisey bulabilirisn belki
    private String productName;

    @Column(name = "QUANTITY", nullable = false)
    private  Integer quantity;

    @Column(name="PRICE", nullable = false)
    private double price;
}
