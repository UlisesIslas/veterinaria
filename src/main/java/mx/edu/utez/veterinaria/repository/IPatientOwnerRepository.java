package mx.edu.utez.veterinaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.edu.utez.veterinaria.entity.PatientOwner;

public interface IPatientOwnerRepository extends JpaRepository<PatientOwner, Integer> {
    
}
