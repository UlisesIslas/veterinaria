package mx.edu.utez.veterinaria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import mx.edu.utez.veterinaria.entity.City;

public interface ICityRepository extends JpaRepository<City, Integer> {
    
    @Query(value = "SELECT * FROM city c WHERE c.state = :id", nativeQuery = true)
    List<City> findByStateId(@Param("id") int id);

}
