package mx.edu.utez.veterinaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.edu.utez.veterinaria.entity.AnimalType;

public interface IAnimalTypeRepository extends JpaRepository<AnimalType, Integer> {
    
}
