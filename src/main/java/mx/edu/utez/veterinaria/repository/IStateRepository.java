package mx.edu.utez.veterinaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.edu.utez.veterinaria.entity.State;

public interface IStateRepository extends JpaRepository<State, Integer> {
    
}
