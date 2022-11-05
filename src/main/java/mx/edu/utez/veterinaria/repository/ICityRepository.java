package mx.edu.utez.veterinaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.edu.utez.veterinaria.entity.City;

public interface ICityRepository extends JpaRepository<City, Integer> {
    
}
