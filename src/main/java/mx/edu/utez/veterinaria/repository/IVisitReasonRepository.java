package mx.edu.utez.veterinaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.edu.utez.veterinaria.entity.VisitReason;

public interface IVisitReasonRepository extends JpaRepository<VisitReason, Integer> {
    
}
