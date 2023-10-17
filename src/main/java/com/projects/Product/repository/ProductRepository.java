package com.projects.Product.repository;

import com.projects.Product.dbmodel.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
