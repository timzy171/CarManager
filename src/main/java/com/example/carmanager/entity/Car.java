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

    @Column(name = "id_mark")
    public String idMark;

    @Column(name = "mark")
    public String mark;

    @Column(name = "rus_mark")
    public String russianMark;

    @Column(name = "is_popular")
    public Boolean isPopular;

    @Column(name = "country")
    public String country;

    @Column(name = "model_id")
    public String modelId;

    @Column(name = "model")
    public String model;

    @Column(name = "rus_model")
    public String russianModel;

    @Column(name = "class")
    public String carClass;

    @Column(name = "year_of")
    public Integer yearOf;

    @Column(name = "year_by")
    public Integer yearBy;
}
