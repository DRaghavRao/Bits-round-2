package com.example.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import entities.SalesPrediction;


@Repository
public interface SalesPredictionRepository extends JpaRepository<SalesPrediction, Long> {}