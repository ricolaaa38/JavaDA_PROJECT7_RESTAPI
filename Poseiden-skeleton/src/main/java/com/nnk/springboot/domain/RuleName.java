package com.nnk.springboot.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.sql.Timestamp;

/**
 * RuleName entity representing a rule in the system.
 * It includes fields for name, description, JSON representation,
 * template, SQL string, and SQL part.
 */
@Entity
@Table(name = "rulename")
@Data
public class RuleName {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Name is required")
    @Column(name = "name")
    private String name;

    @NotBlank(message = "Description is required")
    @Column(name = "description")
    private String description;

    @NotBlank(message = "Json is required")
    @Column(name = "json")
    private String json;

    @NotBlank(message = "Template is required")
    @Column(name = "template")
    private String template;

    @NotBlank(message = "SqlStr is required")
    @Column(name = "sqlStr")
    private String sqlStr;

    @NotBlank(message = "SqlPart is required")
    @Column(name = "sqlPart")
    private String sqlPart;
}
