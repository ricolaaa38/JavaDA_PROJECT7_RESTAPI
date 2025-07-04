package com.nnk.springboot.domain;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.sql.Timestamp;


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
    private Timestamp asOfDate;

    @NotNull(message = "Term est obligatoire")
    @Column(name = "term")
    private Double term;

    @NotNull(message = "Value est obligatoire")
    @Column(name = "value")
    private Double value;

    @NotNull(message = "CreationDate est obligatoire")
    @Column(name = "creationDate")
    private Timestamp creationDate;
}
