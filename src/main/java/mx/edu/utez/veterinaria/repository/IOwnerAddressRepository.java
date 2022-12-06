package mx.edu.utez.veterinaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import mx.edu.utez.veterinaria.entity.OwnerAddress;

public interface IOwnerAddressRepository extends JpaRepository<OwnerAddress, Integer> {

    @Query(value = "SELECT * FROM owner_address oa WHERE oa.owner = :id", nativeQuery = true)
    OwnerAddress findByOwnerId(@Param("id") int id);
    
}
