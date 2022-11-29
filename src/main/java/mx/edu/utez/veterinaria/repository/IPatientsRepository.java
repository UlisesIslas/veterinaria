package mx.edu.utez.veterinaria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import mx.edu.utez.veterinaria.entity.Patients;

public interface IPatientsRepository extends JpaRepository<Patients, Integer> {
    
    @Query(value = "SELECT * FROM patients p WHERE p.owner = :id", nativeQuery = true)
    List<Patients> findByOwnerId(@Param("id") int id);

}
