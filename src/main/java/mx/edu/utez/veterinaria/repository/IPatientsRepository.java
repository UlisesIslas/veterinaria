package mx.edu.utez.veterinaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.edu.utez.veterinaria.entity.Patients;

public interface IPatientsRepository extends JpaRepository<Patients, Integer> {
    
}
