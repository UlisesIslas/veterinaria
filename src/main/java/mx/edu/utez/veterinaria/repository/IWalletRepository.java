package mx.edu.utez.veterinaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import mx.edu.utez.veterinaria.entity.Wallet;

public interface IWalletRepository extends JpaRepository<Wallet, Integer> {

    Wallet findByOwnerId(int id);

    @Query(value = "SELECT w.balance FROM wallet w WHERE w.owner = :id", nativeQuery = true)
    double findWalletBalanceByOwnerId(@Param("id") int id);
    
}
