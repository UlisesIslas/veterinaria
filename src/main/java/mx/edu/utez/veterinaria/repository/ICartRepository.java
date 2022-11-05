package mx.edu.utez.veterinaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.edu.utez.veterinaria.entity.Cart;

public interface ICartRepository extends JpaRepository<Cart, Integer> {
    
}
