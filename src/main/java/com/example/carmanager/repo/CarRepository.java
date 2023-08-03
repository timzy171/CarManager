package com.example.carmanager.repo;

import com.example.carmanager.entity.Car;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CarRepository extends JpaRepository<Car,Integer> {
    @Query("from Car c where lower(c.mark) like concat(:mark,'%')")
    public List<Car> findByMark(@Param("mark") String mark);
}
