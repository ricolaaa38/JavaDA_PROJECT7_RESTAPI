package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.RuleName;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * RuleNameRepository interface for managing RuleName entities.
 * It extends JpaRepository for basic CRUD operations.
 */
public interface RuleNameRepository extends JpaRepository<RuleName, Integer> {
}
