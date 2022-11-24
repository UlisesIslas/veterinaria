package mx.edu.utez.veterinaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.edu.utez.veterinaria.entity.UserConnection;

@Repository
public interface IUserConnectionRepository extends JpaRepository<UserConnection, Long> {
    UserConnection findByUser_Username(String username);
}
