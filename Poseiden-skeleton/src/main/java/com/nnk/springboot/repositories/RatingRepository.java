package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * RatingRepository interface for managing Rating entities.
 * It extends JpaRepository for basic CRUD operations.
 */
public interface RatingRepository extends JpaRepository<Rating, Integer> {

}
