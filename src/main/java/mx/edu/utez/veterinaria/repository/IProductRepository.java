package mx.edu.utez.veterinaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.edu.utez.veterinaria.entity.Product;

public interface IProductRepository extends JpaRepository<Product, Integer> {
    
}
