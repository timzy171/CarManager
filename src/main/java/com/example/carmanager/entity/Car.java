package com.example.carmanager.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    public Integer id;
    @Column(name = "mark")
    public String mark;
    @Column(name = "country")
    public String country;

    @Column(name = "model")
    public String model;
}
