package mx.edu.utez.veterinaria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import mx.edu.utez.veterinaria.entity.Consultory;

public interface IConsultoryRepository extends JpaRepository<Consultory, Integer> {
 
    @Query(value = "SELECT * FROM consultory c WHERE c.visit_reason = :id", nativeQuery = true)
    List<Consultory> findConsultoryByVisitReason(@Param("id") int id);

}
