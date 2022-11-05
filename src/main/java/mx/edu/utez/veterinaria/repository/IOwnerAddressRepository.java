package mx.edu.utez.veterinaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.edu.utez.veterinaria.entity.OwnerAddress;

public interface IOwnerAddressRepository extends JpaRepository<OwnerAddress, Integer> {
    
}
