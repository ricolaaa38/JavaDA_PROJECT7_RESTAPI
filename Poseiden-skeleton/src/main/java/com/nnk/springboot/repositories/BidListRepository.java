package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.BidList;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * BidListRepository interface for managing BidList entities.
 * It extends JpaRepository for basic CRUD operations.
 */
public interface BidListRepository extends JpaRepository<BidList, Integer> {

}
