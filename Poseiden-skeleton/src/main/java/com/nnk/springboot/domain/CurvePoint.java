package com.nnk.springboot.domain;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.sql.Timestamp;


@Entity
@Table(name = "curvepoint")
@Data
public class CurvePoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "CurveId est obligatoire")
    @Column(name = "CurveId")
    private Integer curveId;

    @NotBlank(message = "AsOfDate est obligatoire")
    @Column(name = "asOfDate")
    private Timestamp asOfDate;

    @NotBlank(message = "Term est obligatoire")
    @Column(name = "term")
    private Double term;

    @NotBlank(message = "Value est obligatoire")
    @Column(name = "value")
    private Double value;

    @NotBlank(message = "CreationDate est obligatoire")
    @Column(name = "creationDate")
    private Timestamp creationDate;
}
