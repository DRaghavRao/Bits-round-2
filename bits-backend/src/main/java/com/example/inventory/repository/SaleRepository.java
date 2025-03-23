package com.example.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import entities.Sales;



@Repository
public interface SaleRepository extends JpaRepository<Sales, Long> {}