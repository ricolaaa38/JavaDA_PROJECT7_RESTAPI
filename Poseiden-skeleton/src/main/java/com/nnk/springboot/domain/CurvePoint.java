package com.nnk.springboot.domain;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * CurvePoint entity representing a point on a curve in the system.
 * It includes fields for curve ID, date, term, value, and creation date.
 */
@Entity
@Table(name = "curve_point")
@Data
public class CurvePoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "CurveId est obligatoire")
    @Column(name = "CurveId")
    private Integer curveId;

    @NotNull(message = "AsOfDate est obligatoire")
    @Column(name = "asOfDate")
    private LocalDateTime asOfDate;

    @NotNull(message = "Term est obligatoire")
    @Column(name = "term")
    private Double term;

    @NotNull(message = "Value est obligatoire")
    @Column(name = "value")
    private Double value;

    @NotNull(message = "CreationDate est obligatoire")
    @Column(name = "creationDate")
    private LocalDateTime creationDate;
}
