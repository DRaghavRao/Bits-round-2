package com.example.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import entities.Product;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {}

