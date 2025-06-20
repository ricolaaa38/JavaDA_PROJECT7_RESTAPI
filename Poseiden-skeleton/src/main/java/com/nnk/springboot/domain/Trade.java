package com.nnk.springboot.domain;

import jakarta.persistence.*;
import java.sql.Timestamp;


@Entity
@Table(name = "trade")
public class Trade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    // TODO: Map columns in data table TRADE with corresponding java fields
}
