package mx.edu.utez.veterinaria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import mx.edu.utez.veterinaria.entity.Users;

public interface IUsersRepository extends JpaRepository<Users, Integer> {
    
    Users findByUsername(String username);

    @Query(value = "SELECT * FROM users u WHERE u.role = 2", nativeQuery = true)
    List<Users> findDoctors();

    @Query(value = "SELECT * FROM users u WHERE u.role = :id", nativeQuery = true)
    List<Users> findByRole(@Param("id") int id);

}
