package mx.edu.utez.veterinaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.edu.utez.veterinaria.entity.Tickets;

public interface ITicketsRepository extends JpaRepository<Tickets, String> {
    
}
