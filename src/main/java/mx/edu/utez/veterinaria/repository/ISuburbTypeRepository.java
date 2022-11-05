package mx.edu.utez.veterinaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.edu.utez.veterinaria.entity.SuburbType;

public interface ISuburbTypeRepository extends JpaRepository<SuburbType, Integer> {
    
}
