package mx.edu.utez.veterinaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.edu.utez.veterinaria.entity.Users;

public interface IUsersRepository extends JpaRepository<Users, Integer> {
    
}
