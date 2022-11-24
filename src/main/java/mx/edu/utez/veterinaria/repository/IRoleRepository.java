package mx.edu.utez.veterinaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.edu.utez.veterinaria.entity.Roles;

public interface IRoleRepository extends JpaRepository<Roles, Integer> {
    
}
