package com.example.carmanager.repo;

import com.example.carmanager.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car,Integer> {
}
