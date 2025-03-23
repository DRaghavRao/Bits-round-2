package com.example.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import entities.Supplier;


@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {}
