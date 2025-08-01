package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.Trade;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * TradeRepository interface for managing Trade entities.
 * It extends JpaRepository for basic CRUD operations.
 */
public interface TradeRepository extends JpaRepository<Trade, Integer> {
}
