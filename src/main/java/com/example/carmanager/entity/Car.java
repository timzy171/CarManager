package com.example.carmanager.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "cars",schema = "public")
public class Car {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer id;
    @Column(name = "mark")
    public String mark;
    @Column(name = "country")
    public String country;

    @Column(name = "model")
    public String model;
}