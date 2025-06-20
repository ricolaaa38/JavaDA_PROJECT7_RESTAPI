package com.nnk.springboot.domain;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Table(name = "rating")
@Data
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "MoodysRating est obligatoire")
    @Column(name = "moodysRating")
    private String moodysRating;

    @NotBlank(message = "SandPRating est obligatoire")
    @Column(name = "sandPRating")
    private String sandPRating;

    @NotBlank(message = "FitchRating est obligatoire")
    @Column(name = "fitchRating")
    private String fitchRating;

    @NotBlank(message = "OrderNumber est obligatoire")
    @Column(name = "orderNumber")
    private Integer orderNumber;

}
