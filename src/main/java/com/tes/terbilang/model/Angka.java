package com.tes.terbilang.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "number")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Angka {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "value")
    private Integer value;

    @Column(name = "terbilang")
    private String terbilang;
}
