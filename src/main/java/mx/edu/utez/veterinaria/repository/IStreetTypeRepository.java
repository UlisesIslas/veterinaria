package mx.edu.utez.veterinaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.edu.utez.veterinaria.entity.StreetType;

public interface IStreetTypeRepository extends JpaRepository<StreetType, Integer> {
    
}
