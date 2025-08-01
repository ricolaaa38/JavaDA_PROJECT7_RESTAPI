package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.CurvePoint;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * CurvePointRepository interface for managing CurvePoint entities.
 * It extends JpaRepository for basic CRUD operations.
 */
public interface CurvePointRepository extends JpaRepository<CurvePoint, Integer> {

}
