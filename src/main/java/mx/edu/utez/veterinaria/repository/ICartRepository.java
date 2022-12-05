package mx.edu.utez.veterinaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import mx.edu.utez.veterinaria.entity.Cart;

public interface ICartRepository extends JpaRepository<Cart, Integer> {
    
    @Query(value = "SELECT * FROM cart c WHERE c.schedule = :id", nativeQuery = true)
    Cart findByScheduleId(@Param("id") int id);

}
