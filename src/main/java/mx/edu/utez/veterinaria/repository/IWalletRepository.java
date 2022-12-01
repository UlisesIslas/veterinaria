package mx.edu.utez.veterinaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.edu.utez.veterinaria.entity.Wallet;

public interface IWalletRepository extends JpaRepository<Wallet, Integer> {

    Wallet findByOwnerId(int id);
    
}
