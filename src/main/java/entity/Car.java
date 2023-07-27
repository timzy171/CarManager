package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cars")
public class Car {
    @Id
    public Integer id;

    @Column(name = "id_mark")
    public String idMark;

    @Column(name = "Марка")
    public String mark;

    @Column(name = "Марка_кириллица")
    public String russianMark;

    @Column(name = "Популярная_марка")
    public Boolean isPopular;

    @Column(name = "Страна")
    public String country;

    @Column(name = "model_id")
    public String modelId;

    @Column(name = "Модель")
    public String model;

    @Column(name = "модель_кириллица")
    public String russianModel;

    @Column(name = "Класс")
    public String carClass;

    @Column(name = "Год_от")
    public Integer yearOf;

    @Column(name = "Год_до")
    public Integer yearBy;
}
